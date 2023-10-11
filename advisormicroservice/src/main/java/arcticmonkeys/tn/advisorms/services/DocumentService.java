package arcticmonkeys.tn.advisorms.services;

import arcticmonkeys.tn.advisorms.interfaces.IDocument;
import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.repository.DocumentRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class DocumentService implements IDocument {

    private DocumentRepo documentRepo;
    private  Path fileStorageLocation;

    public DocumentService(DocumentRepo documentRepo){
        this.documentRepo=documentRepo;
        this.fileStorageLocation = Paths.get("uploads")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
        }
    }
    @Override
    public Document createDocument(Document doc) {
        return documentRepo.save(doc);
    }

    @Override
    public Document updateDocument(Document doc) {
        return documentRepo.save(doc);
    }

    @Override
    public Document getDocumentById(Integer id) {
        return documentRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteDocument(Integer id) {
        documentRepo.deleteById(id);
    }

    @Override
    public List<Document> getAll() {
        return documentRepo.findAll();
    }


    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            return null;
        }
    }
}
