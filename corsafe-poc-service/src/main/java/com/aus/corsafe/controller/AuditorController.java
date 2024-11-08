package com.aus.corsafe.controller;

import com.aus.corsafe.entity.Auditor;
import com.aus.corsafe.entity.ResponseModel;
import com.aus.corsafe.response.CommonResponse;
import com.aus.corsafe.service.AuditorService;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuditorController {

    private final AuditorService auditorService;

    /**
     * based on area give auditor's list
     */
    @GetMapping("/getAuditor/{area}")
    public ResponseEntity<ResponseModel<Object>> getAuditorBYArea(@PathVariable("area") String area) {
        List<Auditor> nearsetAuditor = auditorService.getNearsetAuditor(area);
        List<Auditor> allAuditor = auditorService.getAllAuditor();

        Map<String, List<Auditor>> response = new HashMap<>();
        response.put("nearsetAuditor", nearsetAuditor);
        response.put("allAuditor", allAuditor);

        return new CommonResponse<>().prepareSuccessResponseObject(response, HttpStatus.valueOf(200));
    }

    /**
     * all auditor's list
     */
    @JobWorker(type = "ok1", autoComplete = true)
    @GetMapping("/getAllAuditors")

    public ResponseEntity<ResponseModel<Object>> getAllAuditor() {
        log.info("getall auditor's list controller entered");
        List<Auditor> allAuditor = auditorService.getAllAuditor();
        allAuditor.stream().forEach(System.out::println);
        return new CommonResponse<>().prepareSuccessResponseObject(allAuditor, HttpStatus.valueOf(200));
    }

    @JobWorker(type = "ok2", autoComplete = true)
    public void hello() {
        System.out.println("hello method ");
    }


}
