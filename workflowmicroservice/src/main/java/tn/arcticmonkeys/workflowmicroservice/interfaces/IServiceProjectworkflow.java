package tn.arcticmonkeys.workflowmicroservice.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IServiceProjectworkflow  {
    public List<ProjectWorkflow> retrieveAllProjects();
    public List<ProjectWorkflow> retrieveProjectsByCompany(long idCompany) throws Exception;
    public long retrieveProjectByTask(long idTask) throws Exception;
    public ProjectWorkflow addProjectWorkflow (ProjectWorkflow pw) throws Exception;

    public ProjectWorkflow updateProjectWorkflow (ProjectWorkflow pw1, ProjectWorkflow pw2) throws Exception;;
    public ProjectWorkflow retrieveProjectWorkflow (long idProjectWorkflow) throws Exception;
    public void removeProjectWorkflow(long idProjectWorkflow) throws Exception;
    public ProjectWorkflow assignTaskToProject(Task task, long projectId) throws Exception;
    public int estimatedProjectDuration(long projectId) throws Exception;
    public String projectReporting(long idProject) throws Exception;
    public Map<Integer,Integer> returnMostEffectiveMonths(long idCompany) throws Exception;




}
