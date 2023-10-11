package com.example.testdevalidation.service;

import com.example.testdevalidation.entity.*;
import com.example.testdevalidation.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class reponseservice implements Ireponse {
    @Autowired
    reponseRepository rp;
    //userrepository ur;
    questionRepository qr;
    validationRepository vr;

    @Override
    public List<reponse> show() {
        return rp.findAll();
    }

   /* @Override
    public List<reponse> findbyuser(Integer idu) {
        user u = ur.findById(idu).orElse(null);
        return rp.findByUserr(idu);
    }*/


    @Override
    public Integer affee(Integer id, reponse q) {
        question v = qr.findById(id).orElse(null);
        int score = 0;
        if (v.getPropvrai().equals(q.getProp())) {
            return score = 20;
            //return true;

        }
        return score = 0;

    }

    @Override
    public reponse addrep(reponse r) {
        return rp.save(r);
    }


   /*@Override
    public List<reponse> findrepbyid(Integer idr) {
        user u = ur.findById(idr).orElse(null);
       /// reponse r = rp.findById(idr).orElse(null);
        return u.getReponses();
    }*/


}
