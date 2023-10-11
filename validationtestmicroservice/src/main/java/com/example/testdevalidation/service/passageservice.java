package com.example.testdevalidation.service;

import com.example.testdevalidation.entity.*;
import com.example.testdevalidation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class passageservice implements Ipassage {

    @Autowired
    passageRepository pr;
    @Autowired
    validationRepository vr;
    //userrepository ur;
    @Autowired
    Ireponse ir;
    @Autowired
    Ivalidation iv;
    private QrCodeGenerator qrCodeGenerator;
    private final String imagePath = "././src/main/resources/qrcodes/QRCode.png";


    @Override
    public passsage addinscriandassi(Integer idp, Integer idv) {
        passsage p = pr.findById(idp).orElse(null);
        testvalidation v = vr.findById(idv).orElse(null);

        return null;
    }

    @Override
    public passsage addpassa( Integer idu, Integer idv) {
        //passsage p = pr.findById(idp).orElse(null);
        //user u = ur.findById(idu).orElse(null);
        testvalidation v = vr.findById(idv).orElse(null);
        //List<question> w=ur.findByUserr(idu)

        passsage p=new passsage();
        int score = 0;
        p.setIduser(idu);
        p.setTest(v);
        for (question i : v.getQuestions()) {
         // if (i.getReponses().getIduserr()==idu) {
               // System.out.println("hereee");
                if (i.getIdq().equals(i.getReponses().getIdq().getIdq()))

                //if (i.getIdq().equals(i.getReponses().getIdq().getIdq()))
                //if(iv.findquestion(i.getIdq())==i.getReponses().getIdr())
                {
                    System.out.println(i.getPropvrai()+ " "+ i.getReponses().getProp());
                    if (i.getPropvrai().equals(i.getReponses().getProp())) {
                        score += 5;
                    } else score--;
                    //   p.setScore(score);
                }
           //}
            p.setScore(score);
            if(p.getScore()>v.getPts()/2) {
                p.setResultat("succes");
            }
            else
            {
                p.setResultat("echec");
            }

       }


        return pr.save(p);


    }

    @Override
    public List<passsage> showpassage() {
        return pr.findAll();
    }

    @Override
    public passsage showpassagebyid(Integer idp) {
        return pr.findById(idp).orElse(null);
    }

    @Override
    public List<passsage> toplist(Integer nbr) {
        List<passsage> p = pr.findAllByOrderByScoreDesc();
        List<passsage> toplist = new ArrayList<>();
        int count = 0;
        for (passsage i : p) {
                if (count >= nbr) {
                    break;
                }

                toplist.add(i);
                count++;

        }
        return toplist;
    }

    @Override
    public List<passsage> getscorebetween(Integer sc1, Integer sc2) {
        return pr.findByScoreBetween(sc1, sc2);
    }

}
