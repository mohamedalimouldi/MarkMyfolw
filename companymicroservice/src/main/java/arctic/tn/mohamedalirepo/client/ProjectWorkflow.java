package arctic.tn.mohamedalirepo.client;

import arctic.tn.mohamedalirepo.entity.TypeSubscription;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProjectWorkflow implements Serializable {

    private long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date debute;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    private long companyId;


}

