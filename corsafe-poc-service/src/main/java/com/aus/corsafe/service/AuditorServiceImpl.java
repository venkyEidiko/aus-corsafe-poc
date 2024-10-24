package com.aus.corsafe.service;

import com.aus.corsafe.entity.Auditor;
import com.aus.corsafe.repository.AuditorRepository;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuditorServiceImpl implements  AuditorService{


    private final AuditorRepository auditorRepository;

    @Override
    public List<Auditor> getNearsetAuditor(String area) {
        return auditorRepository.findByNearByAreaContaining(area);
    }

    @Override
//    @JobWorker(type = "ok", autoComplete = true)
    public List<Auditor> getAllAuditor() {

        List<Auditor> allAuditors = auditorRepository.findAll();
        log.info("all auditor's::  {allAuditors}",allAuditors);
        allAuditors.stream().forEach(System.out::println);
        return allAuditors;


    }
    @JobWorker(type = "ok", autoComplete = true)
    public void ok(){
        System.out.println("Ok method executed");
    }


}
