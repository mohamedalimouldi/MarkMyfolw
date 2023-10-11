package tn.arcticmonkeys.workflowmicroservice.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;
import tn.arcticmonkeys.workflowmicroservice.interfaces.IServiceTask;
import tn.arcticmonkeys.workflowmicroservice.repositories.ProjectWorkflowRepository;
import tn.arcticmonkeys.workflowmicroservice.repositories.TaskRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ServiceTask implements IServiceTask {
    @Autowired
    private TaskRepository tr;
    private ProjectWorkflowRepository pwr;
    @Override
    public List<Task> retrieveAllTasks() {
        return this.tr.findAll();
    }

    @Override
    public Task addTask(Task task) throws Exception {
        return this.tr.save(task);
    }

    @Override
    public Task updateTask(Task task1, Task task2) throws Exception {
        task1=task2;
        return this.tr.save(task1);
    }

    @Override
    public Task retrieveTask(long idTask) throws Exception {
        return this.tr.findById(idTask).orElse(null);
    }

    @Override
    public void removeTask(long idTask) throws Exception {
        this.tr.deleteById(idTask);
    }

    @Override
    public List<Task> findTasksByProject(long idProject) throws Exception {
        ProjectWorkflow project = this.pwr.findById(idProject).get();
        return project.getTasks();
    }




}
