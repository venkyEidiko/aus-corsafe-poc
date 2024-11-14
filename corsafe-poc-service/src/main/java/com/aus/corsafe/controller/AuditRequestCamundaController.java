package com.aus.corsafe.controller;

import com.aus.corsafe.dto.CompleteTaskDto;
import com.aus.corsafe.dto.CompleteTaskModel;
import com.aus.corsafe.dto.StartCamundadto;
import com.aus.corsafe.entity.Order;
import com.aus.corsafe.entity.auditrequest.ProcessDetails;
import com.aus.corsafe.exceptions.UserNotFoundExceptionCls;
import com.aus.corsafe.model.AssignTask;
import com.aus.corsafe.model.SearchTask;
import com.aus.corsafe.repository.OrderRepo;
import com.aus.corsafe.repository.ProcessDetailsRepository;
import com.aus.corsafe.service.bpmnservice.AuditRequestService;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auditRequest")
@Slf4j
@ToString
@CrossOrigin(origins = "http://localhost:3000/**", allowCredentials = "true")
public class AuditRequestCamundaController {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProcessDetailsRepository processDetailsRepo;
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

//    @PostMapping("/startTask")
//    public Object startCamunda(@RequestBody StartCamundadto dto){
//
//
//        log.info("start camunda controller entered");
//        return auditRequestService.startCamunda(dto);
//    }


    @PostMapping("/startTask")
    public Object startCamunda(@RequestBody StartCamundadto dto) {
        log.info("start camunda controller entered");

        List<Order> orders = orderRepo.findByUserId(dto.getUserId());
        log.info("orders {}",orders);
        if (orders.isEmpty()) {
            throw new UserNotFoundExceptionCls("Order not found for user with ID: " + dto.getUserId());
        }

        for (Order order : orders) {
            Integer orderId = order.getOrderId();
            log.info("Processing order id: {}", order);
            ProcessDetails details = processDetailsRepo.findAllByOrderId(orderId).orElse(null);
            log.info("Process Details : {}",details);
            if (details != null && details.getProcessInstanceKey() != 0) {
                continue;
            }
            // Create a new DTO instance for each order to avoid overwriting
            StartCamundadto orderSpecificDto = new StartCamundadto(
                    dto.getUserId(),
                    dto.getFirstName(),
                    dto.getLastName(),
                    dto.getEmail(),
                    dto.getPhoneNumber(),
                    dto.getAbn(),
                    dto.getCompanyName(),
                    dto.getCompanyAddress(),
                    dto.getState(),
                    dto.getPostalCode(),
                    orderId // Set specific order ID here
            );

            log.info("calling start service from controller ");
            auditRequestService.startCamunda(orderSpecificDto);
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }

        return "Processed " + orders.size() + " orders for user ID: " + dto.getUserId();
    }


}
