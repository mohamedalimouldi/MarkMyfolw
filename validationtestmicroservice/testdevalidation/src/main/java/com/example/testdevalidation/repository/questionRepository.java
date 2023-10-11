package com.example.testdevalidation.repository;

import com.example.testdevalidation.entity.question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface questionRepository extends JpaRepository<question,Integer>{
   // Optional<Object> findById(reponse rep);
    // public List<validation> findByQuestion (Integer idv);
   //public question findBytitre(String titre); bech nekhdemha baad modification
public List<question> findByvalidation(Integer idv);
}
