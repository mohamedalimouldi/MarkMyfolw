package arcticmonkeys.tn.advisorms.repository;

import arcticmonkeys.tn.advisorms.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message,Integer> {
    List<Message> findAllByIdUser(Integer iduser);
}
