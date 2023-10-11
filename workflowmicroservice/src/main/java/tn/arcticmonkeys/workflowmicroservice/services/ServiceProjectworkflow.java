package tn.arcticmonkeys.workflowmicroservice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;
import tn.arcticmonkeys.workflowmicroservice.entities.TaskStatus;
import tn.arcticmonkeys.workflowmicroservice.interfaces.IServiceProjectworkflow;
import tn.arcticmonkeys.workflowmicroservice.repositories.ProjectWorkflowRepository;
import tn.arcticmonkeys.workflowmicroservice.repositories.TaskRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceProjectworkflow implements IServiceProjectworkflow {
    @Autowired
    private ProjectWorkflowRepository pwr;
    private TaskRepository tr;
    @Override
    public List<ProjectWorkflow> retrieveAllProjects() {
        return this.pwr.findAll();
    }

    @Override
    public List<ProjectWorkflow> retrieveProjectsByCompany(long idCompany) throws Exception {
        return this.pwr.findProjectWorkflowByCompanyId(idCompany);
    }

    @Override
    public long retrieveProjectByTask(long idTask) throws Exception {
        return this.pwr.findProjectWorkflowByTaskId(idTask);
    }

    @Override
    public ProjectWorkflow addProjectWorkflow(ProjectWorkflow pw) throws Exception {
        return this.pwr.save(pw);
    }

    @Override
    public ProjectWorkflow updateProjectWorkflow(ProjectWorkflow pw1, ProjectWorkflow pw2) throws Exception {
        pw1=pw2;
        return this.pwr.save(pw1);
    }

    @Override
    public ProjectWorkflow retrieveProjectWorkflow(long idProjectWorkflow) throws Exception {
        return this.pwr.findById(idProjectWorkflow).orElse(null);
    }

    @Override
    public void removeProjectWorkflow(long idProjectWorkflow) throws Exception {
        ProjectWorkflow project ;
        project= pwr.findById(idProjectWorkflow).get();
        if(project.getTasks().size()>0){
            for(Task task:project.getTasks() ){
                this.tr.delete(task);
            }
        }
        this.pwr.deleteById(idProjectWorkflow);
    }
    @Override
    public ProjectWorkflow assignTaskToProject(Task task, long projectId) throws Exception {
        ProjectWorkflow project= this.pwr.findById(projectId).get();
        List<Task> tasks = project.getTasks();
        tasks.add(task);
        //task.setStatus(TaskStatus.DRAFT);
        task.setProject(project);
        project.setTasks(tasks);
        this.tr.save(task);
        return this.pwr.saveAndFlush(project);
    }

    @Override
    public int estimatedProjectDuration(long projectId) throws Exception {
        int totalDuration = 0;
        ProjectWorkflow project = this.pwr.findById(projectId).get();
        List<Task> tasks = project.getTasks();
        for (Task task : tasks) {
            long duration = task.getDeadline().getTime() - task.getDebute().getTime();
            duration = duration / (1000 * 60 * 60 * 24);
            totalDuration += duration+1;
        }
        return totalDuration;
    }
    @Override
    public String projectReporting(long idProject) throws Exception {
        ProjectWorkflow project = this.pwr.findById(idProject).get();
        List<Long> nbContributedUsers = this.pwr.retrieveContributedUsers(idProject);
        double projectCost = 0;
        if(project.getTasks().size()>0) {
            projectCost = this.pwr.retrieveProjectCost(idProject);
        }
        String reports = "Project : "+project.getName()+"\n";
        reports+=project.getDebute()+" - "+project.getDeadline()+"\n\n";
        for (Task task:project.getTasks()
        ) {

            reports+="Task name : "+task.getName()+"\nTask description : "+task.getDescription()+"\nDebute date : "+task.getDebute()+"\nDeadline : "+task.getDeadline().toString()+
                    "\nTask status : "+task.getStatus()+"\nTask price : "+task.getPrice()+" USD"+"\n----------\n";

        }
        reports+="\n\nProject Status : "+project.getStatus();
        reports+="\nEstimated days of work : "+this.estimatedProjectDuration(idProject);
        reports+="\nContributed Users : "+nbContributedUsers.size()+" users.";
        reports+="\nProject Cost : "+projectCost+" USD.";
        return reports;
    }

    @Override
    public Map<Integer, Integer> returnMostEffectiveMonths(long idCompany) throws Exception {

        Map<Integer,Integer> mostEffectiveMonths = new HashMap<>();
        List<ProjectWorkflow> companyProjects = this.pwr.findProjectWorkflowByCompanyId(idCompany);
        for (ProjectWorkflow project : companyProjects){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(project.getDebute());
            int month = calendar.get(Calendar.MONTH) + 1;
            mostEffectiveMonths.put(month, mostEffectiveMonths.getOrDefault(month, 0) + 1);

        }

        return mostEffectiveMonths;
    }


}
