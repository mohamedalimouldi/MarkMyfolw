package arcticmonkeys.tn.advisorms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Intent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIntent;
    private String tag;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> patterns=new HashSet<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> responses=new HashSet<>();

    private String context;
    public Intent(){
        patterns=new HashSet<>();
        responses=new HashSet<>();
    }
}
