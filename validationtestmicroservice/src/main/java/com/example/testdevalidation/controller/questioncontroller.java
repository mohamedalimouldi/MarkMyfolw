package com.example.testdevalidation.controller;

import com.example.testdevalidation.entity.question;
import com.example.testdevalidation.service.Iquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class questioncontroller {
    @Autowired
    Iquestion Iq;
    @PostMapping("/addquestion")
    public question add(@RequestBody question q )
    {
        return Iq.addq(q);

    }

    @GetMapping(    "/showallquestion")
    public List<question> show()
    {
        return Iq.showall();
    }

    @PostMapping("/addquestion/{idv}")
    public question addincri(@RequestBody question ins, @PathVariable("idv") Integer idv)
    {
        return Iq.addinscriandassi(ins, idv);
    }

    @PutMapping("/updatequestion")
    public question update(@RequestBody question q)
    {
        return Iq.updateq(q);
    }

    @DeleteMapping("/deletequestion/{id}")
    public void delet(@PathVariable("id") Integer idq)
    {
        Iq.deleteq(idq);
    }

    @GetMapping("/showquestion/{id}")
    public question getquestion(@PathVariable("id") Integer idq)
    {
        return  Iq.showquest(idq);
    }

}
