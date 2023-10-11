package arcticmonkeys.tn.advisorms.controllers;

import arcticmonkeys.tn.advisorms.interfaces.IDocument;
import arcticmonkeys.tn.advisorms.interfaces.IGeneratedDoc;
import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.models.GeneratedDocument;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("document")
public class DocumentController {
    private IDocument iDocument;
    private IGeneratedDoc iGenDoc;
    public DocumentController(IDocument iDocument,IGeneratedDoc iGenDoc){

        this.iDocument=iDocument;
        this.iGenDoc=iGenDoc;
    }
    @PostMapping("/add")
    public Document add(@RequestBody Document sk){
        return iDocument.createDocument(sk);

    }
    @GetMapping("/all")
    public List<Document> getall(){
        //return iDocument.();
        return iDocument.getAll();
    }

    @GetMapping("/getById/{id}")
    public Document getById(@PathVariable("id") Integer id){
        return iDocument.getDocumentById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Document deleteDoc=iDocument.getDocumentById(id);
        iDocument.deleteDocument(id);
        return "deleted";
    }
    @GetMapping(
            value = "/get-file/{path}"
    )
    public ResponseEntity<?> getFile(@PathVariable("path")String path) throws IOException {
        Path dirPath = Paths.get("uploads/"+path);

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + path + "\"";
        UrlResource uri=new UrlResource(dirPath.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(uri);
    }
    @PutMapping("/update")
    public Document update(@RequestBody Document dk){
        return iDocument.updateDocument(dk);
    }

    @PostMapping("/uploadFile")
    public Map<String,String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = iDocument.storeFile(file);
        Map<String,String> map=new HashMap<>();
        map.put("file",fileName);

        return map;
    }

    @PostMapping("/requestdoc/{id}")
    public GeneratedDocument processFile(@RequestBody GeneratedDocument sk,@PathVariable("id") Integer id) {

        return this.iGenDoc.generatedDocument(sk,id);
    }

}
