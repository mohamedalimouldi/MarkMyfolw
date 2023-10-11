package arcticmonkeys.tn.advisorms.services;

import arcticmonkeys.tn.advisorms.interfaces.IGeneratedDoc;
import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.models.GeneratedDocument;
import arcticmonkeys.tn.advisorms.repository.DocumentRepo;
import arcticmonkeys.tn.advisorms.repository.GeneratedDocRepo;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class GeneratedDocService implements IGeneratedDoc {
    private GeneratedDocRepo gendocumentRepo;
    private DocumentRepo documentRepo;

    public GeneratedDocService(GeneratedDocRepo gendocumentRepo,DocumentRepo documentRepo ){
        this.gendocumentRepo=gendocumentRepo;
        this.documentRepo=documentRepo;

    }

    @Override
    public GeneratedDocument generatedDocument(GeneratedDocument gdoc,Integer id)  {
        Document docSearched=documentRepo.findById(id).orElse(null);
        gdoc.setDocument(docSearched);
        String filepath = "uploads/"+gdoc.getDocument().getPath();
        String replacementText = "";
        System.out.println(filepath);
        try  {
            XWPFDocument doc = this.openDocument(filepath);
            System.out.println("here");
            XWPFDocument newDoc = this.replaceText(doc, "", "");
            if (doc != null) {
                System.out.println("here33");
                for (Map.Entry<String, String> set :
                        gdoc.getFieldsToMap().entrySet()) {

                    // Printing all elements of a Map
                    System.out.println(set.getKey() + " = "
                            + set.getValue());
                    newDoc = this.replaceText(newDoc, set.getKey(), set.getValue());
                }
                String name=gdoc.getDocument().getText()+gdoc.getIdUser()+".docx";
                this.saveDocument(newDoc, "uploads/"+name);
                gdoc.setPath(name);
                gdoc=gendocumentRepo.save(gdoc);
                return gdoc;
            }
            System.out.println("got here");
            return null;
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
            return null;
        }

    }

    @Override
    public GeneratedDocument requestDocument(GeneratedDocument gdoc) {
        return gendocumentRepo.save(gdoc);
    }


    private XWPFDocument replaceText(XWPFDocument doc, String findText, String replaceText)  {
        List<XWPFParagraph> paragraphs = doc.getParagraphs();

// Loop through each paragraph
        for (XWPFParagraph paragraph : paragraphs) {
            // Get all runs of text in the paragraph
            List<XWPFRun> runs = paragraph.getRuns();

            // Loop through each run of text
            for (XWPFRun run : runs) {
                // Get the text of the run
                String text = run.getText(0);

                // Check if the run contains the text you want to replace
                if (text != null && text.contains(findText)) {
                    // Replace the text in the run
                    text = text.replace(findText, replaceText);
                    run.setText(text, 0);
                }
            }
        }

        //doc.close();

        return doc;
    }


    private XWPFDocument openDocument(String file) throws Exception {
        //URL res = getClass().getClassLoader().getResource(file);
        try {
            XWPFDocument document = null;

            document = new XWPFDocument(new FileInputStream(
                    new File(file)));

            return document;
        }
        catch ( Exception e){
            return null;
        }
    }

    private void saveDocument(XWPFDocument doc, String file) {
        try (FileOutputStream out = new FileOutputStream(file)) {
            doc.write(out);
        } catch (IOException e) {

            System.out.println("Exception");
        }
    }
}
