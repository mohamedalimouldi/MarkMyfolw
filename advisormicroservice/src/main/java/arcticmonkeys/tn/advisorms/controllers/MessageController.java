package arcticmonkeys.tn.advisorms.controllers;

import arcticmonkeys.tn.advisorms.interfaces.IMessage;
import arcticmonkeys.tn.advisorms.models.Intent;
import arcticmonkeys.tn.advisorms.models.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private IMessage iMessage;
    public MessageController(IMessage iMessage){
        this.iMessage=iMessage;
    }
    @PostMapping("/add")
    public Message add(@RequestBody Message sk){

        return iMessage.createMessage(sk);

    }
    @GetMapping("/all/{id}")
    public List<Message> getall(@PathVariable("id") Integer id){
        //return iMessage.();
        return iMessage.getAll(id);
    }

    @GetMapping("/getById/{id}")
    public Message getById(@PathVariable("id") Integer id){
        return iMessage.getMessagebyId(id);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        iMessage.deleteMessage(id);
    }
    @PutMapping("/update")
    public Message update(@RequestBody Message dk){
        return iMessage.updateMessage(dk);
    }

}
