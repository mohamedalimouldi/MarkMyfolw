package com.example.testdevalidation.controller;

import com.example.testdevalidation.entity.*;
import com.example.testdevalidation.repository.validationRepository;
import com.example.testdevalidation.service.*;
import com.example.testdevalidation.service.passageservice;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;

@RestController
//@RequestMapping("/test")
@AllArgsConstructor
public class validationcontroller {
    @Autowired
    Ivalidation Iv;
    @Autowired
    validationRepository vr;
    @Autowired
    validationservice vs;

    @PostMapping("/addvalidation")
    public testvalidation add(@RequestBody testvalidation v)
    {
        return Iv.addv(v);
    }
    @GetMapping("/showallvalidation")
    public List<testvalidation> show()
    {
        return Iv.showv();
    }
    @GetMapping("/showvalidation/{idv}")
    public testvalidation showva(@PathVariable("idv") Integer idv)
    {
       return Iv.showvalid(idv);

    }

    @PutMapping("/updatevalidation")
    public testvalidation updt(@RequestBody testvalidation v )
    {
        return Iv.updatev(v);
    }

    @DeleteMapping("/deletevalidation/{id}")
    public void deletev(@PathVariable("id") Integer idv)
    {
        Iv.deletev(idv);
    }

   // @PostMapping("/uploadimage/{id}")
    //public ResponseEntity<testvalidation> uploadImage(@RequestBody testvalidation test, @RequestPart("file") MultipartFile file) throws Exception {
       //        String Path_Directory="C:\\Users\\admin\\Desktop\\canada\\testdevalidation\\src\\main\\resources\\static\\image";
      //testvalidation test= vr.findById(id).orElse(null);
        // Files.copy(file.getInputStream(), Paths.get(Path_Directory+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        //test.setImage(Base64.getEncoder().encodeToString(file.getBytes()));

        //try {
            //Iv.uploadImage(file,id);
            //return ResponseEntity.ok("Image uploaded successfully");
            //} catch (IOExc    eption e) {
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        //}
      //  try {
        //    testvalidation v = Iv.uploadImage(test,file);
          //  return ResponseEntity.ok(v);
        //} catch (IOException e) {
          //  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        //}
    //}

    @GetMapping({"/Qrtotest/{idv}"})
    public ResponseEntity<String> callwithQrCode(@PathVariable("idv") Integer idv){
       return vs.callwithQrCode(idv);
       // byte[] imageBytes = QrCodeGenerator.generateImageQRCode();
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.IMAGE_PNG);
        //return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    @PostMapping("/addquestiontotest/{id}")
    public testvalidation addd(@RequestBody List<question> q ,@PathVariable("id") Integer id)
    {
        return vs.addd(q,id);
    }

}
