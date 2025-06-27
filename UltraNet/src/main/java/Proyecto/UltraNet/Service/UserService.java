package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Dto.UserDto;
import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Repository.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepositoryJpa repository;

    public List <User> getAllUsers() {
        return repository.findAll();
    }

    public User addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setType(userDto.getType());
        user.setPassword(userDto.getPassword());
        return repository.save(user);
    }

    public User updateUser(UserDto userDto) {
        if (repository.existsById(userDto.getId())) {
            User user = repository.findById(userDto.getId()).get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setType(userDto.getType());
            user.setPassword(userDto.getPassword());
            return repository.save(user);
        } else {
            return null;
        }
    }

    public boolean userExistsById(Integer id) {
        return repository.existsById(id);
    }

    public boolean userExistsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public User findUserById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteUser(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public boolean isInvalidPassword(String password){
        if(password.length()<8 || password.length()>16){
            return true;
        }
        return false;
    }

    public boolean isInvalidType(String type){
        if(type.equalsIgnoreCase("Client") || type.equalsIgnoreCase("Administrator")){
            return false;
        }
        return true;
    }

    public User editUser(User user) {
        if (repository.existsById(user.getId())) {
            User userBD = repository.findById(user.getId()).get();
            if (user.getName() != null) {
                userBD.setName(user.getName());
            }
            if (user.getEmail() != null) {
                userBD.setEmail(user.getEmail());
            }
            if (user.getType() != null) {
                userBD.setType(user.getType());
            }
            if (user.getPassword() != null) {
                userBD.setPassword(user.getPassword());
            }
            return repository.save(userBD);
        } else {
            return null;
        }
    }
}
