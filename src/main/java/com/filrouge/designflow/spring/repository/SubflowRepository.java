package com.filrouge.designflow.spring.repository;

import com.filrouge.designflow.spring.model.Subflow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubflowRepository extends JpaRepository<Subflow, Long> {

    Optional<Subflow> findByName(String subflowName);
}
