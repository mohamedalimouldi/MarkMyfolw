package arcticmonkeys.tn.advisorms.interfaces;

import arcticmonkeys.tn.advisorms.models.Document;
import arcticmonkeys.tn.advisorms.models.Message;

import java.util.List;

public interface IMessage {
    Message createMessage(Message message);
    Message updateMessage(Message messages);
    Message getMessagebyId(Integer id);
    List<Message> getMessagesByUser(Integer sender);
    void deleteMessage(Integer id);
    List<Message> getAll(Integer id);

}
