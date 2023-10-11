package tn.arcticmonkeys.workflowmicroservice.interfaces;

import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;

import java.util.List;

public interface IServiceTask {
    public List<Task> retrieveAllTasks();
    public Task addTask (Task task) throws Exception;

    public Task updateTask (Task task1, Task task2) throws Exception;;
    public Task retrieveTask (long idTask) throws Exception;
    public void removeTask(long idTask) throws Exception;
    public List<Task> findTasksByProject(long idProject) throws Exception;
 }
