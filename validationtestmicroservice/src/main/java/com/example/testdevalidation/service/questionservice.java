package com.example.testdevalidation.service;

import com.example.testdevalidation.entity.*;
import com.example.testdevalidation.repository.*;
import com.example.testdevalidation.repository.validationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class questionservice implements Iquestion{
    @Autowired
    questionRepository Q;
    validationRepository V;
   // userrepository ur;

    @Override
    public question addq(question q) {
        return Q.save(q) ;
    }

    @Override
    public List<question> showall() {
        return Q.findAll();
    }

    @Override
    public question showquest(Integer idq) {
        return Q.findById(idq).orElse(null);
    }

    @Override
    public question addinscriandassi( question q, Integer idv) {
        testvalidation s = V.findById(idv).orElse(null);
        q.setValidation(s);
        return Q.save(q);
    }

    @Override
    public question updateq(question q) {
        return Q.save(q);
    }

    @Override
    public void deleteq(Integer idq) {
        Q.deleteById(idq);

    }


    /*@Override
    public boolean affect(Integer id, String rep) {
        question q=Q.findById(id).orElse(null);

        int score = 0;

        //for (reponse i : reponses) {
        //Integer reponse = Integer.valueOf(String.valueOf(reponses.getReponse()));
        //question q = qr.findById(reponse).orElse(null);
        //question q = (question) qr.findById(i.getReponse()).orElse();
        if ( rep.equals(q.getPropvrai())) {
            score++;
           return true;
        }
        else return false ;

    }*/


}
