package tn.esprit.pidev.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Repository.UserRepository;
import tn.esprit.pidev.entity.User;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor

public class UserService implements IUser {
    private final UserRepository UR;

    @Override
    public List<User> getAllUsers() {
        return UR.findAll();
    }

    @Override
    public void updateUser(User u) {
        UR.save(u);
    }

    @Override
    public User deleteUser(String u) {
         User u1=this.UR.findByUsername(u);
        this.UR.delete(u1);
        return u1;
    }
    //connected user !
}
