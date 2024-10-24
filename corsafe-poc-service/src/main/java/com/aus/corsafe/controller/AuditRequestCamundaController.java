package com.aus.corsafe.controller;

import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.model.AssignTask;
import com.aus.corsafe.model.SearchTask;
import com.aus.corsafe.service.bpmnservice.AuditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auditRequest")
public class AuditRequestCamundaController {

    @Autowired
    AuditRequestService auditRequestService;

    @PostMapping("/completeTask")
    public String completeTask(@RequestBody CompleteTaskModel completeTask){
        return auditRequestService.completeTask(completeTask);
    }

    @PostMapping("/assignTask")
    public Object assignTask(@RequestBody AssignTask assignTask){
        return auditRequestService.getAssignTask(assignTask);
    }


    @GetMapping("/searchTask")
    public Object searchTask(@RequestBody SearchTask searchTask){
        return auditRequestService.getSearchTask(searchTask);
    }

}
