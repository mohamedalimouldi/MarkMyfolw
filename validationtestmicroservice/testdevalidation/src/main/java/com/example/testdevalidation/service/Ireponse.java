package com.example.testdevalidation.service;

import com.example.testdevalidation.entity.question;
import com.example.testdevalidation.entity.reponse;

import java.util.List;

public interface Ireponse {
public List<reponse> show();
//public List<reponse> findbyuser(Integer idu);
public Integer affee(Integer id, reponse q);
    public reponse addrep(reponse r);
//public Integer score(Integer id, reponse q);

   // public List<reponse> findrepbyid(Integer idu);


}
