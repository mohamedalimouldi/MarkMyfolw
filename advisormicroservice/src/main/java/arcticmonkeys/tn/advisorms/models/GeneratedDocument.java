package arcticmonkeys.tn.advisorms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class GeneratedDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocument;
    private String text;
    private Integer idUser;
    private String path;
    @ManyToOne
    private Document document;



    @Transient
    private Map<String, String> fieldsToMap;
    public GeneratedDocument(){
        fieldsToMap=new HashMap<>();
    }
}
