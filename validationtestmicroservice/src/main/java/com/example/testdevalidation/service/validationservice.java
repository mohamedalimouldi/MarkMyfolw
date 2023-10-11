package com.example.testdevalidation.service;

import com.example.testdevalidation.entity.QrCodeRequest;
import com.example.testdevalidation.entity.question;
import com.example.testdevalidation.entity.testvalidation;
import com.example.testdevalidation.repository.validationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class validationservice implements Ivalidation {

    @Autowired
    validationRepository VR;
    private QrCodeGenerator qrCodeGenerator;
    private final String imagePath = "././src/main/resources/qrcodes/QRCode.png";

    @Override
    public testvalidation addv(testvalidation v) {


        return VR.save(v);

}

    @Override
    public List<testvalidation> showv() {
        return VR.findAll();
    }

    @Override
    public testvalidation showvalid(Integer idv) {
        return VR.findById(idv).orElse(null);
    }

    @Override
    public testvalidation updatev(testvalidation v) {
        return VR.save(v);
    }

    @Override
    public void deletev(Integer idv) {
        VR.deleteById(idv);
    }

    @Override
    public List<question> findquestion(Integer idv) {
        return VR.findByQuestions(idv);
    }

    @Override
    public testvalidation addd(List<question> q, Integer idv) {
        testvalidation test= VR.findById(idv).orElse(null);
        test.setQuestions(q);
        for(question i:q)
        {
            i.setValidation(test);
        }

        return VR.save(test);
    }


    // @Override
    //public testvalidation uploadImage(testvalidation test,MultipartFile file) throws IOException {
        //testvalidation v =VR.findById(id).orElse(null);
         //v.setImage(file.getBytes());
       // v.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
      //  test.setImage(file.getBytes());
        //return VR.save(test);
    //}



    public ResponseEntity<String> callwithQrCode(Integer idv) {
        //user u=ur.findById(idu).orElse(null);
        //passsage p = pr.findById(idu).orElse(null);
        testvalidation t = VR.findById(idv).orElse(null);
       // if (t != null) {
            QrCodeRequest qrcoderequest = new QrCodeRequest();
            String url = "http://localhost:4200/test/vu/" + String.valueOf(idv);
            qrcoderequest.setUrl(url);
            String a = qrCodeGenerator.generateImageQRCode(QrCodeRequest.getUrl(), 250, 250, imagePath);
            // return ("Qr code generated succeffully");
            if (a != null) {
                return ResponseEntity.ok(a);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating QR code");
            }
       // }
    }
        //else return ("test de validation n'existe pas");

    }










