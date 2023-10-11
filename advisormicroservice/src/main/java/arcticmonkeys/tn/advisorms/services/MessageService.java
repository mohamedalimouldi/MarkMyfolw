package arcticmonkeys.tn.advisorms.services;

import arcticmonkeys.tn.advisorms.dto.ChatGptRequestDto;
import arcticmonkeys.tn.advisorms.externalapi.ChatGPTClient;
import arcticmonkeys.tn.advisorms.externalapi.NeonClient;
import arcticmonkeys.tn.advisorms.interfaces.IMessage;
import arcticmonkeys.tn.advisorms.models.Intent;
import arcticmonkeys.tn.advisorms.models.Message;
import arcticmonkeys.tn.advisorms.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MessageService implements IMessage {
    private MessageRepo messageRepo;
    private NeonClient neonClient;
    private  ChatGPTClient chatGptFeignClient;

    public MessageService(MessageRepo messageRepo,NeonClient neonClient,ChatGPTClient chatGptFeignClient){

        this.messageRepo=messageRepo;
        this.neonClient=neonClient;
        this.chatGptFeignClient=chatGptFeignClient;
    }
    @Override
    public Message createMessage(Message message) {
        String res=this.neonClient.search(message.getText());
        System.out.println(res);
        if (res.equals("Its seems like I cannot understand your question")){


            ChatGptRequestDto req=new ChatGptRequestDto();

            req.setPrompt(message.getText());
            req.setMaxTokens(5);
            try{
                List<String> responses=this.chatGptFeignClient.askQuestion(req).getChoices();
                System.out.println(responses);
                res=responses.get(0);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                res="ChatGPT not available try later";
            }
        }
        System.out.println(res);
        message.setDate(new Date());
        message.setResponse(res);
        return messageRepo.save(message);

    }
    @Override
    public List<Message> getAll(Integer id) {
        return messageRepo.findAllByIdUser(id);

    }
    @Override
    public Message updateMessage(Message messages) {
        return messageRepo.save(messages);
    }

    @Override
    public Message getMessagebyId(Integer id) {
        return messageRepo.findById(id).orElse(null);
    }

    @Override
    public List<Message> getMessagesByUser(Integer sender) {
        return messageRepo.findAll();
    }

    @Override
    public void deleteMessage(Integer id) {
        Message m=messageRepo.findById(id).orElse(null);
        if (m!=null){
             messageRepo.delete(m);
        }

    }
}
