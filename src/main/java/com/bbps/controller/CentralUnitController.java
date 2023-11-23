package com.bbps.controller;

import com.bbps.constants.APIMappingConstant;
import com.bbps.model.billerfetch.BillerFetchRequest;
import com.bbps.service.BillerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CentralUnitController {

    @Autowired
    BillerService billerService;


    @PostMapping(value = APIMappingConstant.BILL_FETCH_REQUEST+APIMappingConstant.ACK_REQUEST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getBillFetchCU(@RequestBody String billFetchRequest) {
        log.info("BILL - Fetch Request Received [{}]",billFetchRequest);
        return ResponseEntity.status(HttpStatus.OK).body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bbps:Ack xmlns:bbps=\"http://bbps.org/schema\" api=\"BillFetchRequet\" refId=\"LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689\" msgId=\"MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689\" RspCd=\"Successful/VALIDATION_ERR/DUPLICATE_REQ\" ts=\"2019-05-16T15:25:50+05:30\">\n" +
                "<errorMessages>null</errorMessages>\n" +
                "<api>BillFetchRequets</api>\n" +
                "<refId>LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689</refId>\n" +
                "<msgId>MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689</msgId>\n" +
                "<ts>2019-05-16T15:25:50+05:30</ts>\n" +
                " </bbps:Ack>");
    }

    @PostMapping(value = APIMappingConstant.BILLER_FETCH_REQUEST+APIMappingConstant.ACK_REQUEST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> getBillerFetchCU(@RequestBody String billerFetchRequest) {
        log.info("BILLER - Fetch Request Received [{}]",billerFetchRequest);
        return ResponseEntity.status(HttpStatus.OK).body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bbps:Ack xmlns:bbps=\"http://bbps.org/schema\" api=\"BillerFetchRequet\" refId=\"LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689\" msgId=\"MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689\" RspCd=\"Successful/VALIDATION_ERR/DUPLICATE_REQ\" ts=\"2019-05-16T15:25:50+05:30\">\n" +
                "<errorMessages>null</errorMessages>\n" +
                "<api>BillerFetchRequets</api>\n" +
                "<refId>LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689</refId>\n" +
                "<msgId>MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689</msgId>\n" +
                "<ts>2019-05-16T15:25:50+05:30</ts>\n" +
                " </bbps:Ack>");
    }

    @PostMapping(value = APIMappingConstant.BILL_PAYMENT_REQUEST+APIMappingConstant.ACK_REQUEST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> billpayment(@RequestBody String billerFetchRequest) {
        log.info("PAYMENT - Fetch Request Received [{}]",billerFetchRequest);
        return ResponseEntity.status(HttpStatus.OK).body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bbps:Ack xmlns:bbps=\"http://bbps.org/schema\" api=\"BillPaymentRequest\" refId=\"LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689\" msgId=\"MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689\" RspCd=\"Successful/VALIDATION_ERR/DUPLICATE_REQ\" ts=\"2019-05-16T15:25:50+05:30\">\n" +
                "<errorMessages>null</errorMessages>\n" +
                "<api>BillPaymentRequest</api>\n" +
                "<refId>LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689</refId>\n" +
                "<msgId>MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689</msgId>\n" +
                "<ts>2019-05-16T15:25:50+05:30</ts>\n" +
                " </bbps:Ack>");
    }
    @PostMapping(value = APIMappingConstant.BILL_VALIDATION_REQUEST+APIMappingConstant.ACK_REQUEST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> billValidation(@RequestBody String billerFetchRequest) {
        log.info("PAYMENT - Fetch Request Received [{}]",billerFetchRequest);
        return ResponseEntity.status(HttpStatus.OK).body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bbps:Ack xmlns:bbps=\"http://bbps.org/schema\" api=\"BillPaymentRequest\" refId=\"LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689\" msgId=\"MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689\" RspCd=\"Successful/VALIDATION_ERR/DUPLICATE_REQ\" ts=\"2019-05-16T15:25:50+05:30\">\n" +
                "<errorMessages>null</errorMessages>\n" +
                "<api>BillPaymentRequest</api>\n" +
                "<refId>LNMSQQR4RKT7X1UGPY7JGUV454PL9T2C689</refId>\n" +
                "<msgId>MPPSQRS4ROT7X1MGPY7JGUV444PLTU2C689</msgId>\n" +
                "<ts>2019-05-16T15:25:50+05:30</ts>\n" +
                " </bbps:Ack>");
    }



}
