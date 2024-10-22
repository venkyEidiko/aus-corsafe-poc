package com.aus.corsafe.service;

import com.aus.corsafe.entity.Auditor;
import com.aus.corsafe.repository.AuditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditorServiceImpl implements  AuditorService{


    private final AuditorRepository auditorRepository;

    @Override
    public List<Auditor> getNearsetAuditor(String area) {
        return auditorRepository.findByNearByAreaContaining(area);
    }

    @Override
    public List<Auditor> getAllAuditor() {
        return auditorRepository.findAll();

    }


}
