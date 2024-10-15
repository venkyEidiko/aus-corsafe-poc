package com.aus.corsafe.repository;


import com.aus.corsafe.entity.SecurityQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityQuestionKeyRepository extends JpaRepository<SecurityQuestionKey,Integer> {
}
