package com.example.testdevalidation.controller;

import com.example.testdevalidation.entity.*;
import com.example.testdevalidation.repository.passageRepository;
import com.example.testdevalidation.repository.questionRepository;
import com.example.testdevalidation.repository.validationRepository;
import com.example.testdevalidation.service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class passagecontroller {
    @Autowired
    validationRepository vr;
    @Autowired
    questionRepository qr;
    @Autowired
    passageRepository pr;
    @Autowired
    Ipassage Ip;
    @Autowired
    passageservice ps;

    @GetMapping("/allpassages")
    public List<passsage> shh()
    {
       return  Ip.showpassage();
    }

    @PostMapping("/addpassage/{idu}/{idv}")
    public passsage passagescore(@PathVariable("idu") Integer idu,@PathVariable("idv") Integer idv)
    {
        return  Ip.addpassa(idu,idv);
    }

    @GetMapping("/toppassage/{nbr}")
    public List<passsage> toppassage(@PathVariable("nbr") Integer nbr)
    {
        return  Ip.toplist(nbr);
    }
    @GetMapping("/scorebetween")
    public List<passsage> getpassagebyscore(@RequestParam("a") Integer x, @RequestParam("b") Integer y)
    {
        return Ip.getscorebetween(x,y);

    }
    @GetMapping("/getpassagebyid/{idp}")
    public passsage getpassagebyid(@PathVariable("idp") Integer idp)
    {
        return Ip.showpassagebyid(idp);
    }



    }


