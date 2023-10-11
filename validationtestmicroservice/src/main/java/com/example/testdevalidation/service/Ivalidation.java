package com.example.testdevalidation.service;
//import tn.esprit.Spring_Project.entity.validation;

import com.example.testdevalidation.entity.question;
import com.example.testdevalidation.entity.testvalidation;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;

public interface Ivalidation {


    public testvalidation addv(testvalidation v );
    public List<testvalidation> showv();
    public testvalidation showvalid(Integer idv);
    public testvalidation updatev(testvalidation v);
    public void deletev(Integer idv);
    public List<question> findquestion(Integer idv);
    public testvalidation addd(List<question> q , Integer idv);
   //
    // testvalidation uploadImage(testvalidation test, MultipartFile file) throws IOException;



}
