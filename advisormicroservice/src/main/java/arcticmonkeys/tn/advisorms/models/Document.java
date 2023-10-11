package arcticmonkeys.tn.advisorms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Document  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocument; // Clé primaire
    private String text;
    private String path;

    @Column(name = "fields")
    @ElementCollection
    private List<String> fields;
    public Document(){
        fields=new ArrayList<>();
    }

}
