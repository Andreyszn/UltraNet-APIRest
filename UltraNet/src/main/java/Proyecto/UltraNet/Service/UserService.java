package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public ArrayList<User> getAll(){return repository.getAll();}

    public User add(User user){
        return repository.add(user);
    }

    public User delete(int id){
        return repository.delete(id);
    }

    public User searchUser(int id){
        return repository.search(id);
    }

    public User editUser(User user){
        return repository.edit(user);
    }

    public User updateUser(User user){
        return repository.update(user);
    }
}
