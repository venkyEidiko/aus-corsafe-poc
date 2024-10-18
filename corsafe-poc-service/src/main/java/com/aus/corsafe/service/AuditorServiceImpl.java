package com.aus.corsafe.service;

import com.aus.corsafe.entity.Auditor;
import com.aus.corsafe.repository.AuditorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditorServiceImpl implements AuditorService {

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
