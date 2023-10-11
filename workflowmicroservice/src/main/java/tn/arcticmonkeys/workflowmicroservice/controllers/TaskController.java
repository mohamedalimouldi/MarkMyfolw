package tn.arcticmonkeys.workflowmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.arcticmonkeys.workflowmicroservice.PDFgenerator;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;
import tn.arcticmonkeys.workflowmicroservice.interfaces.IServiceTask;
import tn.arcticmonkeys.workflowmicroservice.repositories.TaskRepository;
import tn.arcticmonkeys.workflowmicroservice.services.ServiceTask;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private IServiceTask st;

    @PostMapping("/add")
    public Task add(@RequestBody Task t) throws Exception {
        return st.addTask(t);
    }

    @PutMapping("/update/{id}")
    public Task update(@RequestBody Task newTask,@PathVariable("id") long id) throws Exception{
        Task oldTask;
        oldTask= st.retrieveTask(id);
        newTask.setId(id);
        newTask.setProject(oldTask.getProject());
        return st.updateTask(oldTask,newTask);
    }

    @GetMapping("/get/{id}")
    public Task getById(@PathVariable("id") long id) throws Exception {
        return st.retrieveTask(id);
    }

    @GetMapping("/getAll")
    public List<Task> getAll(){
        return st.retrieveAllTasks();
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        st.removeTask(id);
    }

    @GetMapping("/getByProject/{id}")
    public List<Task> getByProject(@PathVariable("id") long projectId) throws Exception{
        return this.st.findTasksByProject(projectId);

    }
}
