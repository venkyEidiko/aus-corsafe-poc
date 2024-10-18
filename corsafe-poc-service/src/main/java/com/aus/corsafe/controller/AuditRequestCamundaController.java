package com.aus.corsafe.controller;

import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.response.CommonResponse;
import com.aus.corsafe.service.bpmnservice.AuditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auditRequest")
public class AuditRequestCamundaController {

    @Autowired
    AuditRequestService auditRequestService;

    @PostMapping("/completeTask")
    public ResponseEntity<ResponseModel<Object>> completeTask(@RequestBody CompleteTaskModel completeTask){
        String message = auditRequestService.completeTask(completeTask);
        return new CommonResponse<>().prepareSuccessResponseObject(message, HttpStatus.valueOf(200));
    }
}
