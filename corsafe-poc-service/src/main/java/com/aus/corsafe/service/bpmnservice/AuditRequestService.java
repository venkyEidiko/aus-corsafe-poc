package com.aus.corsafe.service.bpmnservice;

import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.entity.auditrequest.ProcessDetails;
import com.aus.corsafe.repository.ProcessDetailsRepository;
import com.aus.corsafe.repository.UserRegisterRepo;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class AuditRequestService {

    @Autowired
    ZeebeClient client;

    @Autowired
    UserRegisterRepo userRegisterRepo;

    @Autowired
    ProcessDetailsRepository processDetailsRepository;

    @JobWorker(type = "userDetailsVerifier", autoComplete = true)
    public void GettingVariableAndSettingVariable(final ActivatedJob job) {
        log.info("strted userDetailsVerifier");
        System.out.println("strted userDetailsVerifier");

        boolean knowMe = true;

        String userEmail = (String) job.getVariable("email");
        Optional<UserRegister> user = userRegisterRepo.findByEmail(userEmail);
        knowMe = user.isPresent();
        Map<String, Object> variables = new HashMap<>();
        variables.put("knowMe", knowMe);
        log.info("updated knowMe: {}", knowMe);
        System.out.println("updated knowMe");

        ProcessDetails processDetails = ProcessDetails.builder()
                .firstName((String) job.getVariable("firstName"))
                .lastName((String) job.getVariable("lastName"))
                .abn((String) job.getVariable("abn"))
                .email((String) job.getVariable("email"))
                .companyName((String) job.getVariable("companyName"))
                .companyAddress((String) job.getVariable("companyAddress"))
                .state((String) job.getVariable("state"))
                .postalCode((String) job.getVariable("postalCode"))
                .phoneNumber((long) (job.getVariable("phoneNumber")))
                .processInstanceKey(job.getProcessInstanceKey())
                .taskId(job.getKey())
                .createdAt(new Date())
                .assignee(job.getCustomHeaders().get("assignee"))
                .implementation(job.getType())
                .build();

        log.info("prepared processDetails: {}", processDetails);
        System.out.println("prepared processDetails");
        processDetailsRepository.save(processDetails);

        client.newSetVariablesCommand(job.getElementInstanceKey())
                .variables(variables)
                .send()
                .join();

        log.info("exists from userDetailsVerifier");
        System.out.println("exits from userDetailsVerifier");
    }


    @JobWorker(type = "auditChargeCalculation", autoComplete = true)
    public void chargeCalculation(ActivatedJob job) {
        System.out.println("Calculating charges");
    }


    public String completeTask(CompleteTaskModel completeTask) {

        client.newCompleteCommand(completeTask.getTaskId())
                .variables(completeTask.getVariables())
                .send()
                .join();

        return "Task Completed with jobKey: " + completeTask.getTaskId();
    }
}
