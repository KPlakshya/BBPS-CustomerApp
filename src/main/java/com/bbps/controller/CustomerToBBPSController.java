package com.bbps.controller;

import com.bbps.config.ErrorResponse;
import com.bbps.constants.APIMappingConstant;
import com.bbps.model.billeronboard.BillerFetchRequest;
import com.bbps.model.billfetch.BillFetchRequest;
import com.bbps.model.billpayment.BillPaymentRequest;
import com.bbps.model.billvalidation.BillValidationRequest;
import com.bbps.service.BillerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value="/bbps/customerapp/")
public class CustomerToBBPSController {

    @Autowired
    BillerService billerService;

    @PostMapping(value = APIMappingConstant.BILLER_FETCH_REQUEST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillerFetchRequest(  @RequestBody BillerFetchRequest billerFetchRequest) {
        log.info("BILLER - Fetch Request Received [{}]",billerFetchRequest);
       Object response= billerService.processRequest(billerFetchRequest,APIMappingConstant.BILLER_FETCH_REQUEST);
    return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = APIMappingConstant.BILL_FETCH_REQUEST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillFetchRequest( @RequestBody BillFetchRequest billFetchRequest) {
        log.info("BILL - Fetch Request Received [{}]",billFetchRequest);
        Object response= billerService.processRequest(billFetchRequest,APIMappingConstant.BILL_FETCH_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = APIMappingConstant.BILL_VALIDATION_REQUEST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillValidationRequest( @RequestBody BillValidationRequest billValidationRequest) {
        log.info("BILL - Fetch Request Received [{}]",billValidationRequest);
        Object response= billerService.processRequest(billValidationRequest,APIMappingConstant.BILL_VALIDATION_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = APIMappingConstant.BILL_PAYMENT_REQUEST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getBillPaymentRequest(@RequestBody BillPaymentRequest billPaymentRequest) {
        log.info("BILL - Fetch Request Received [{}]",billPaymentRequest);
        Object response= billerService.processRequest(billPaymentRequest,APIMappingConstant.BILL_PAYMENT_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(value = APIMappingConstant.AGENT_FETCH_REQUEST,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAgentFetchRequest(@RequestBody BillValidationRequest billValidationRequest) {
        log.info("BILL - Fetch Request Received [{}]",billValidationRequest);
        Object response= billerService.processRequest(billValidationRequest,APIMappingConstant.AGENT_FETCH_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }


}
