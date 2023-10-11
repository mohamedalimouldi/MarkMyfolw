package com.example.testdevalidation.controller;

import com.example.testdevalidation.entity.*;
import com.example.testdevalidation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class reponsecontroller {
    @Autowired
    Ireponse ip;
    @GetMapping("/reponses")
    public List<reponse> affich()
    {
        return ip.show();
    }
    @PostMapping("/verification/{id}")
    public Integer verif(@PathVariable("id") Integer id,@RequestBody reponse q)
    {
        return ip.affee(id,q);

    }
    @PostMapping("/addreponse")
    public reponse add(@RequestBody reponse r )
    {
        return ip.addrep(r);

    }

}
