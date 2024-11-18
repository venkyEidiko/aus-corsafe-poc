package com.aus.corsafe.controller;

import com.aus.corsafe.dto.CompleteTaskDto;
import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.dto.StartCamundadto;
import com.aus.corsafe.model.AssignTask;
import com.aus.corsafe.model.SearchTask;
import com.aus.corsafe.service.bpmnservice.AuditRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auditRequest")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000/**", allowCredentials = "true")
public class AuditRequestCamundaController {

    @Autowired
    AuditRequestService auditRequestService;

    // for complete the task with using zeebe client
    @PostMapping("/completeTask")
    public Object completeTask(@RequestBody CompleteTaskModel completeTask) {
        return auditRequestService.completeTaskWithZeebe(completeTask);
    }

    //complete the task with rest api
    @PostMapping("/completeTask1")
    public Object completeTask1(@RequestBody CompleteTaskDto cm) {
        return auditRequestService.completeTask(cm);
    }

    //assign the task
    @PostMapping("/assignTask")
    public Object assignTask(@RequestBody AssignTask assignTask) {
        return auditRequestService.getAssignTask(assignTask);
    }

    //search the task
    @GetMapping("/searchTask")
    public Object searchTask(@RequestBody SearchTask searchTask) {
        log.info("search task controller entered");

        return auditRequestService.getSearchTask(searchTask);
    }


    //for to start the camunda
    @PostMapping("/startTask")
    public Object startCamunda(@RequestBody StartCamundadto dto){
        log.info("start camunda controller entered");
        return auditRequestService.startCamunda(dto);
    }

}

