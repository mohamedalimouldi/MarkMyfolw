package tn.arcticmonkeys.workflowmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@ToString
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="Task_Name")
    @NonNull
    private String name;
    @NonNull
    private String description;
    private boolean payed;
    @Enumerated(EnumType.STRING)
    private TaskStatus status=TaskStatus.DRAFT;
    @Temporal(TemporalType.DATE)
    @Column(name="Task_Debute_Date")
    @NonNull
    private Date debute;
    @Temporal(TemporalType.DATE)
    @Column(name="Task_Deadline")
    @NonNull
    private Date deadline;
    @NonNull
    private double price;
    @NonNull
    private long userId;
    @ManyToOne
    private ProjectWorkflow project;

    @JsonBackReference(value="tp")
    public ProjectWorkflow getProject() {
        return project;
    }

    public void setProject(ProjectWorkflow project) {
        this.project = project;
    }





}
