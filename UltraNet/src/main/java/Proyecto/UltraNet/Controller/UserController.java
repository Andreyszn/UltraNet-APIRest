package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    private ArrayList<User> users;

    @GetMapping
    public ResponseEntity <?> getAllUsers() {
        users = (ArrayList<User>) service.getAllUsers();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.status(204).body("No hay usuarios registrados");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        if (service.findUserById(id) != null) {
            return ResponseEntity.ok(service.findUserById(id));
        } else {
            return ResponseEntity.status(404).body("No se encuentra un usuario con el id " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null || user.getType() == null || user.getPassword() == null) {
            return ResponseEntity.status(400).body("Error: Todos los campos son obligatorios.");
        }
        if (service.UserExistsById(user.getId())) {
            return ResponseEntity.status(400).body("Error: Ya existe un usuario con el ID " + user.getId());
        }

        if (service.userExistsByEmail(user.getEmail())) {
            return ResponseEntity.status(400).body("Error: Ya existe un usuario con el email " + user.getEmail());
        }

        if (user.getId() != null) {
            return ResponseEntity.status(400).body("Error: No se debe proporcionar un ID al crear un nuevo usuario.");
        }
        User createdUser = service.addUser(user);
        return ResponseEntity.ok("Usuario creado con éxito: " + createdUser);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (service.UserExistsById(user.getId())) {
            User updatedUser = service.updateUser(user);
            return ResponseEntity.ok("Usuario actualizado con éxito: " + updatedUser);
        } else {
            return ResponseEntity.status(404).body("No se encuentra un usuario con el ID " + user.getId());
        }
    }

    @PatchMapping
    public ResponseEntity <?> editUser (@RequestBody User user){
        if (service.UserExistsById(user.getId())) {
            User editedUser = service.editUser(user);
            return ResponseEntity.ok("Usuario editado con éxito: " + editedUser);
        } else {
            return ResponseEntity.status(404).body("No se encuentra un usuario con el ID " + user.getId());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        if (service.UserExistsById(id)) {
            service.deleteUser(id);
            return ResponseEntity.ok("Usuario con ID " + id + " eliminado con éxito.");
        } else {
            return ResponseEntity.status(404).body("No se encuentra un usuario con el ID " + id);
        }
    }
}