package arctic.tn.mohamedalirepo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String number;
    private String Email;
    private String codefiscal;
    private String nameuser;
    private String Lastnuser;
    private String emailUser;
    private String password;

    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    private Set<Subscription> subscription;

}
