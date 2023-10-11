package tn.esprit.pidev.Service;

import tn.esprit.pidev.entity.User;

import java.util.List;

public interface IUser {
    public List<User> getAllUsers();
    public void updateUser(User u);
    public User deleteUser(String u);



}
