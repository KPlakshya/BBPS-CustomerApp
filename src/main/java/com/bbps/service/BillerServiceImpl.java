package com.bbps.service;

import com.bbps.constants.BillerContsants;
import com.bbps.entity.CustomerRequestResponse;
import com.bbps.kafka.KafkaMessagePostingService;
import com.bbps.repository.CustomerReqRespRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class BillerServiceImpl implements  BillerService{

    @Autowired
    CustomerReqRespRepo customerReqRespRepo;

    @Autowired
    KafkaMessagePostingService kafkaMessagePostingService;

    @Value("${bbps.posting.request}")
    String customerPostingReqTopic;

    @Override
    public Object processRequest(Object reqObject, String reqAPIType) {
        Map<String, Object> headersMap = new HashMap<>();

        try {
            CustomerRequestResponse customerReqResp = this.setRequestEntityValues(reqObject, reqAPIType);
            customerReqRespRepo.save(customerReqResp);

            headersMap.put(BillerContsants.REQ_TYPE, reqAPIType);
            headersMap.put(BillerContsants.CUSTOMER_REQ_ID, customerReqResp.getId());

            kafkaMessagePostingService.postMessage(reqObject, headersMap, customerPostingReqTopic);
            headersMap.put(BillerContsants.STATUS, BillerContsants.PENDING);

        }catch(JsonProcessingException Jpe){
            log.info("JsonProcessingException while Pushing into topic [{}] , Request [{}]",customerPostingReqTopic,reqObject);
            headersMap.put(BillerContsants.STATUS, BillerContsants.FAILURE_RESP_MESSAGE);
        }catch (Exception e){
            log.info("General Exception while Pushing into topic [{}] , Request [{}]",customerPostingReqTopic,reqObject);
            headersMap.put(BillerContsants.STATUS, BillerContsants.FAILURE_RESP_MESSAGE);


        }
        return headersMap;
    }


    private CustomerRequestResponse setRequestEntityValues(Object requestObject,String reqAPIType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CustomerRequestResponse customerReqResp=new CustomerRequestResponse();
        customerReqResp.setRequest(mapper.writeValueAsString(requestObject));
        customerReqResp.setRequestType(reqAPIType);
        customerReqResp.setRequestTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        customerReqResp.setStatus(BillerContsants.PENDING);
        return customerReqResp;

    }
}
