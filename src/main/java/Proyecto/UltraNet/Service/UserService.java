package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Dto.LoginDto;
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

    public User findUserByEmail(String email){ return repository.findByEmail(email); }

    public void deleteUser(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    public boolean isInvalidType(String type){
        if(type.equalsIgnoreCase("Client") || type.equalsIgnoreCase("Administrador")){
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

    public User loginByEmail(LoginDto loginDto){
        User activateUser = repository.findByEmail(loginDto.getEmailUser());
        activateUser.setActive(true);
        repository.save(activateUser);
        return activateUser;
    }

    public void exitLogin(User user){
        User diactivateUser = repository.findByEmail(user.getEmail());
        diactivateUser.setActive(false);
        repository.save(diactivateUser);
    }

    public boolean existsByPassword(String password){
        return repository.existsByPassword(password);
    }

    public User userActive(){
        List<User> users = getAllUsers();
        if(!users.isEmpty()){
            for(int element=0;element<users.size();element++){
                if (users.get(element).isActive()){
                    return users.get(element);
                }
            }
        }
        return new User();
    }
}
