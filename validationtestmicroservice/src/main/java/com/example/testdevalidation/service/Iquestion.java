package com.example.testdevalidation.service;
//import tn.esprit.Spring_Project.entity.question;
import com.example.testdevalidation.entity.question;
import com.example.testdevalidation.entity.testvalidation;

import java.util.List;

public interface Iquestion {

    public question addq(question q);

    public List<question> showall();
    public question showquest(Integer idq);

    public question addinscriandassi(question q, Integer idv);

    public question updateq(question q);
    public void deleteq(Integer idq);

}
