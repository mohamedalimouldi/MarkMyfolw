package tn.arcticmonkeys.workflowmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectStatus;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;
import tn.arcticmonkeys.workflowmicroservice.interfaces.IServiceProjectworkflow;

import java.util.Date;
import java.util.List;

@Component
public class ProjectScheduler {
    @Autowired
    private IServiceProjectworkflow spw;

    @Scheduled(cron = "0 0 0 * * ?") // run every day at midnight
    public void ActivateOrCompleteProjects() throws Exception {
        List<ProjectWorkflow> projects = this.spw.retrieveAllProjects();
        for (ProjectWorkflow project:projects){
            ProjectWorkflow p1 = project;

            if (project.getDebute().compareTo(new Date())==0) {

                //SETTING THE PROJECT STATUS TO ACTIVE IF TODAY IS ITS DEBUTE DATE
                p1.setStatus(ProjectStatus.ACTIVE);
                this.spw.updateProjectWorkflow(project,p1);

            }else if (project.getDeadline().compareTo(new Date())==0){

                //SETTING THE PROJECT STATUS TO COMPLETED IF TODAY IS ITS DEADLINE
                p1.setStatus(ProjectStatus.COMPLETED);
                this.spw.updateProjectWorkflow(project,p1);

            }
        }

    }

}
