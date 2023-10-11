package arcticmonkeys.tn.advisorms.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
public class Message implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String text;
    // TO BE FIXED
    private Integer idUser;
    private String response;

    private Date date=new Date();
    @ManyToOne
    private  Intent intent;



}
