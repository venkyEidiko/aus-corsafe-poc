package com.aus.corsafe.repository;

import com.aus.corsafe.entity.auditRequest.ProcessDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessDetailsRepository extends JpaRepository<ProcessDetails,Integer> {
}
