package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;
    private ArrayList<User> users;

    @GetMapping
    public ArrayList<User> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id){
        return service.searchUser(id);
    }

    @PostMapping
    public User postUser(@RequestBody User user){
        return service.add(user);
    }

    @PutMapping
    public User putUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable int id) {
        return service.delete(id);
    }

    @PatchMapping
    public User patchUser(@RequestBody User user){
        return service.editUser(user);
    }

}
