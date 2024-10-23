package com.aus.corsafe.service.bpmnservice;

import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.entity.UserRegister;
import com.aus.corsafe.model.AssignTask;
import com.aus.corsafe.model.SearchTask;
import com.aus.corsafe.repository.UserRegisterRepo;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class AuditRequestService {


    private final WebClient webClient;

    public AuditRequestService(WebClient webClient) {
        this.webClient = webClient;
    }


    @Autowired
    ZeebeClient client;

    @Autowired
    UserRegisterRepo userRegisterRepo;


    @JobWorker(type = "userDetailsVerifier", autoComplete = true)
    public void GettingVariableAndSettingVariable(final ActivatedJob job) {
        boolean knowMe = true;

        String userEmail = (String) job.getVariable("email");
        Optional<UserRegister> user = userRegisterRepo.findByEmail(userEmail);

        knowMe = user.isPresent() ? true : false;
        Map<String, Object> variables = new HashMap<>();
        variables.put("knowMe", knowMe);

        long elementInstanceKey = job.getElementInstanceKey();

        client.newSetVariablesCommand(elementInstanceKey)
                .variables(variables)
                .send()
                .join();

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


    public Object getAssignTask(AssignTask assignTask) {
        Object claimed = webClient.patch()

                .uri("/tasks/6755399441234580/assign")
                .body(Mono.just(assignTask), assignTask.getClass())
                .retrieve()
                .bodyToMono(Object.class)
                .block();
        return claimed;
    }

    public List<Object> getSearchTask(SearchTask searchTask) {
        List<Object> list = new ArrayList<>();

        webClient.post()
                .uri("/tasks/search")
                .body(Mono.just(searchTask), searchTask.getClass())
                .retrieve()
                .bodyToFlux(Object.class)
                .doOnNext(list::add) // Add each received object to the list
                .blockLast(); // Wait for the Flux to complete

        return list;
    }
}
