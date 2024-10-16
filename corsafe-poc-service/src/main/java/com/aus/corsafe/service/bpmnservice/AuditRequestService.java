package com.aus.corsafe.service.bpmnservice;

import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.repository.UserRegisterRepo;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AuditRequestService {

    @Autowired
    ZeebeClient client;

    @Autowired
    UserRegisterRepo userRegisterRepo;

    @ZeebeWorker(type = "userDetailsVerifier", autoComplete = true)
    public void GettingVariableAndSettingVariable(final JobClient jobClient, final ActivatedJob activatedJob){
        boolean knowMe=true;

        String userEmail = (String)activatedJob.getVariable("userEmail");
        Optional<UserRegister> user = userRegisterRepo.findByEmail(userEmail);

        knowMe = user.isPresent()?true:false;
        Map<String, Object> variables = new HashMap<>();
        variables.put("knowMe", knowMe);

        long elementInstanceKey = activatedJob.getElementInstanceKey();

        client.newSetVariablesCommand(elementInstanceKey)
                .variables(variables)
                .send()
                .join();

    }

    @ZeebeWorker(type = "auditChargeCalculation", autoComplete = true)
    public void chargeCalculation(final JobClient jobClient, final ActivatedJob activatedJob){
        System.out.println("Calculating charges");
    }
    public String completeTask(CompleteTaskModel completeTask){

        client.newCompleteCommand(completeTask.getTaskId())
                .variables(completeTask.getVariables())
                .send()
                .join();

        return "Task Completed with jobKey: "+completeTask.getTaskId();
    }
}
