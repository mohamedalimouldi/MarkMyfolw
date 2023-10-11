package tn.arcticmonkeys.workflowmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.arcticmonkeys.workflowmicroservice.PDFgenerator;
import tn.arcticmonkeys.workflowmicroservice.entities.ProjectWorkflow;
import tn.arcticmonkeys.workflowmicroservice.entities.Task;
import tn.arcticmonkeys.workflowmicroservice.interfaces.IServiceProjectworkflow;
import tn.arcticmonkeys.workflowmicroservice.services.ServiceProjectworkflow;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("projectworkflow")
public class ProjectworkflowController {
    @Autowired
    private IServiceProjectworkflow spw;
    @PostMapping("/add")
    public ProjectWorkflow add(@RequestBody ProjectWorkflow pw) throws Exception {
        return spw.addProjectWorkflow(pw);
    }

    @PutMapping("/update/{id}")
    public ProjectWorkflow update(@RequestBody ProjectWorkflow newProjectWorkflow, @PathVariable("id") long id) throws Exception {
        ProjectWorkflow oldProject;
        oldProject= spw.retrieveProjectWorkflow(id);
        newProjectWorkflow.setId(id);
        newProjectWorkflow.setTasks(oldProject.getTasks());
        return spw.updateProjectWorkflow(oldProject,newProjectWorkflow);
    }

    @GetMapping("/get/{id}")
    public ProjectWorkflow getById(@PathVariable("id") long id) throws Exception {
        return spw.retrieveProjectWorkflow(id);
    }
    @GetMapping("/getAll/{id}")
    public List<ProjectWorkflow> getAll(@PathVariable("id") long id) throws Exception {
        return spw.retrieveProjectsByCompany(id);
    }
    @GetMapping("/getByTaskId/{id}")
    public long getByTaskId(@PathVariable("id") long id) throws Exception {
        return spw.retrieveProjectByTask(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id) throws Exception {
        spw.removeProjectWorkflow(id);
    }
    
    @PutMapping("/assignTask/{projectId}")
    public ProjectWorkflow assignTaskToProject(@RequestBody Task task, @PathVariable("projectId") long projectId) throws Exception {
        ProjectWorkflow project = spw.assignTaskToProject(task,projectId);
        return project;
    }

    @GetMapping("/estimatedDuration/{projectId}")
    public String estimatedProjectDuration(@PathVariable("projectId") long projectId) throws Exception {
        int duration = spw.estimatedProjectDuration(projectId);
        return duration + " days of work";
    }

    @GetMapping(value = "/export/pdf/{idProject}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> exportToPDF(@PathVariable("idProject") long idProject, HttpServletResponse response) throws Exception {

        String tasks = this.spw.projectReporting(idProject);
        List<String> data = Arrays.asList(tasks.toString());
        ByteArrayInputStream bis = PDFgenerator.generatePdf(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    @GetMapping("/effective-months/{companyId}")
    public Map<Integer, Integer> getMostEffectiveMonths(@PathVariable("companyId") long companyId) throws Exception {
        return spw.returnMostEffectiveMonths(companyId);
    }


}
