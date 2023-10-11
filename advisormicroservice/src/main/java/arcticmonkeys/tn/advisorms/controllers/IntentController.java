package arcticmonkeys.tn.advisorms.controllers;

import arcticmonkeys.tn.advisorms.interfaces.IIntent;
import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.models.Intent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("intent")
public class IntentController {

    private IIntent iIntent;
    public IntentController(IIntent iIntent){
        this.iIntent=iIntent;
    }
    @PostMapping("/add")
    public Intent add(@RequestBody Intent sk){
        return iIntent.createIntent(sk);

    }
    @GetMapping("/all")
    public List<Intent> getall(){
        //return iIntent.();
        return iIntent.getAll();
    }
    @GetMapping("/updateIntents")
    public String UpdateIntents(){
        //return iIntent.();
        return iIntent.updateIntent();
    }
    @Scheduled(cron = "3 56 * * * *")
    public String UpdateIntentsCron(){
        //return iIntent.();
        return iIntent.updateIntent();
    }
    @GetMapping("/getById/{id}")
    public Intent getById(@PathVariable("id") Integer id){
        return iIntent.getIntentById(id);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        iIntent.deleteIntent(id);
        return "Done";
    }
    @PutMapping("/update")
    public Intent update(@RequestBody Intent dk){
        return iIntent.updateIntent(dk);
    }
}
