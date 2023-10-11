package arcticmonkeys.tn.advisorms.interfaces;

import arcticmonkeys.tn.advisorms.models.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDocument {
    Document createDocument(Document doc);
    Document updateDocument(Document doc);
    Document getDocumentById(Integer id);
    void deleteDocument(Integer id);
    List<Document> getAll();
    String storeFile(MultipartFile file);
}
