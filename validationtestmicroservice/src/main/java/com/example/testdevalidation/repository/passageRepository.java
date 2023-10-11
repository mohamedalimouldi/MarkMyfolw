package com.example.testdevalidation.repository;

import com.example.testdevalidation.entity.passsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface passageRepository extends JpaRepository<passsage,Integer> {

    public List<passsage> findAllByOrderByScoreDesc();
    public List<passsage> findByScoreBetween(Integer minscore, Integer maxscore);

    //public passsage findByUser(Integer idu);
}
