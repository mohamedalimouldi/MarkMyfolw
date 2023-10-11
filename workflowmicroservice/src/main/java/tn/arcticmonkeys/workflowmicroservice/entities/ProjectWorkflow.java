package tn.arcticmonkeys.workflowmicroservice.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class ProjectWorkflow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String name;

    @Column(name="Project_Description")
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(name="Project_Deadline")
    @NonNull
    private Date deadline;
    @Temporal(TemporalType.DATE)
    @Column(name="Project_Debute")
    @NonNull
    private Date debute;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status = ProjectStatus.ON_HOLD;

    @NonNull
    private long companyId;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @JsonManagedReference(value="tp")
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
