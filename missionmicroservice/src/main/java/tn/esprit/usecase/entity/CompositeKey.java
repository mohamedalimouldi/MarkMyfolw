package tn.esprit.usecase.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter

@Embeddable
public class CompositeKey  implements Serializable {

//    @Column(name = "idPart")
//    private Integer idPart;

    @Column(name = "idMission")
    private Integer idMission;

}
