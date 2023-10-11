package com.example.testdevalidation.repository;

import com.example.testdevalidation.entity.question;
import com.example.testdevalidation.entity.testvalidation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface validationRepository extends JpaRepository<testvalidation, Integer> {
    //public List<validation> findByQuestion (Integer idv);
    public List<question> findByQuestions(Integer idv);
    public testvalidation findByTitre(String titre);
}
