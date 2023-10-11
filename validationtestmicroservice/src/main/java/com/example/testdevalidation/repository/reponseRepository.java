package com.example.testdevalidation.repository;

import com.example.testdevalidation.entity.reponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reponseRepository extends JpaRepository<reponse,Integer> {
    //public List<reponse> findByUserr(Integer idu);
    //public reponse findreponseByidr(Integer idr);
}
