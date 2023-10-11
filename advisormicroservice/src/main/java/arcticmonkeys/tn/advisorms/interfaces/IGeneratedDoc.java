package arcticmonkeys.tn.advisorms.interfaces;

import arcticmonkeys.tn.advisorms.models.GeneratedDocument;

import java.io.IOException;

public interface IGeneratedDoc {

     GeneratedDocument generatedDocument(GeneratedDocument gdoc,Integer id);
     GeneratedDocument requestDocument(GeneratedDocument gdoc);
}
