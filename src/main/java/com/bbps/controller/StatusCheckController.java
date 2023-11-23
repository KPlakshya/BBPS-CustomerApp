package com.bbps.controller;

import com.bbps.config.ErrorResponse;
import com.bbps.constants.APIMappingConstant;
import com.bbps.model.statusCheckRequest;
import com.bbps.service.StatusCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value="/bbps/customerapp/")
public class StatusCheckController {

    @Autowired
    StatusCheckService statusCheckService;

    @PostMapping(value = APIMappingConstant.STATUS_CHECK,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getStatus(@Valid @RequestBody statusCheckRequest request) {
        log.info("StatusCheck - Request Received [{}]",request);
       Object response= statusCheckService.fetchStatus(request);
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
