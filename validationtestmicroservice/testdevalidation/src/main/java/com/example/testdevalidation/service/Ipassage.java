package com.example.testdevalidation.service;

import com.example.testdevalidation.entity.*;

import java.util.List;

public interface Ipassage {
        public passsage addinscriandassi(Integer idp, Integer idv);
       // public passsage  addpassage(Integer idp, Integer idu, Integer idt);
        public passsage addpassa(Integer idu,Integer idv);
        public List<passsage> showpassage();
        public passsage showpassagebyid(Integer idp);
        public List<passsage> toplist(Integer nbr);
        public List<passsage> getscorebetween(Integer sc1,Integer sc2);
}
