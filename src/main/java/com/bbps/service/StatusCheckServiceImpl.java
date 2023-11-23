package com.bbps.service;


import com.bbps.constants.RespMsgContsants;
import com.bbps.entity.CustomerRequestResponse;
import com.bbps.model.statusCheckRequest;
import com.bbps.model.statusCheckResponse;
import com.bbps.repository.CustomerReqRespRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.bbps.constants.RespMsgContsants.*;

@Service
@Slf4j
public class StatusCheckServiceImpl implements  StatusCheckService {

    @Autowired
    CustomerReqRespRepo customerReqRespRepo;

    @Override
    public Object fetchStatus(statusCheckRequest request) {

        statusCheckResponse response=new statusCheckResponse();
       try {
           Optional<CustomerRequestResponse> customerRequestResponseOptional = customerReqRespRepo.findById(Long.valueOf(request.getCustomerReqId()));

           if (customerRequestResponseOptional.isPresent()) {
               CustomerRequestResponse customerRequestResponse = customerRequestResponseOptional.get();
               response.setReqType(customerRequestResponse.getRequestType());
               response.setCustomerReqId(String.valueOf(customerRequestResponse.getId()));
               if(RespMsgContsants.STATUS_INITIATED.equalsIgnoreCase(customerRequestResponse.getStatus())){
                   response.setStatus(STATUS_PENDING);
               }else {
                   response.setStatus(customerRequestResponse.getStatus());
               }
               response.setRequestTimestamp(customerRequestResponse.getRequestTimestamp());
               response.setResponseTimestamp(customerRequestResponse.getResponseTimestamp());
               response.setResponse(customerRequestResponse.getResponse());
           } else {
               response.setReqType(request.getReqType());
               response.setCustomerReqId(request.getCustomerReqId());
               response.setResponse(INVALID_REQID);
               response.setRequestTimestamp(Timestamp.valueOf(LocalDateTime.now()));
               response.setResponseTimestamp(Timestamp.valueOf(LocalDateTime.now()));

           }
       }catch (Exception e){
           log.error("Exception While Fetching status Request [{}] Exception [{}]",request,e.getMessage());
           response.setReqType(request.getReqType());
           response.setCustomerReqId(request.getCustomerReqId());
           response.setResponse(INTERNAL_ERROR);
           response.setRequestTimestamp(Timestamp.valueOf(LocalDateTime.now()));
           response.setResponseTimestamp(Timestamp.valueOf(LocalDateTime.now()));
       }

        return response;
    }
}
