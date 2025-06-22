package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = service.getAllUsers();
        if (users == null || users.isEmpty()) {
            return ResponseEntity.status(404).body("No hay usuarios registrados.");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        User user = service.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("No se encuentra un usuario con el ID " + id);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if ( user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getType() == null) {
            return ResponseEntity.status(400).body("Error: Todos los campos son obligatorios.");
        }

        if (service.userExistsByEmail(user.getEmail())) {
            return ResponseEntity.status(400).body("Error: Ya existe un usuario con el email " + user.getEmail());
        }

        if(service.isInvalidPassword(user.getPassword())){
            return ResponseEntity.status(400).body("Error: La contraseña debe ser mayor a 8 caracteres y menor a 16 caracteres ");
        }

        if(service.isInvalidType(user.getType())){
            return ResponseEntity.status(400).body("Error: El espacio type debe ser igual a client o admin");
        }
        User createdUser = service.addUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (user.getId() == null){
            return ResponseEntity.status(400).body("Error: El ID del usuario es obligatorio para actualizar.");
        }
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null || user.getType() == null) {
            return ResponseEntity.status(400).body("Error: Todos los campos son obligatorios.");
        }
        if (!service.userExistsById(user.getId())) {
            return ResponseEntity.status(404).body("Error: No se encuentra un usuario con el ID " + user.getId());
        }
        if(service.isInvalidPassword(user.getPassword())){
            return ResponseEntity.status(400).body("Error: La contraseña debe ser mayor a 8 caracteres y menor a 16 caracteres ");
        }
        if(service.isInvalidType(user.getType())){
            return ResponseEntity.status(400).body("Error: El espacio type debe rellenado con Client o Administrator");
        }
        User updatedUser = service.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping
    public ResponseEntity <?> editUser (@RequestBody User user){
        if (user.getId() == null) {
            return ResponseEntity.status(400).body("Error: El ID del usuario es obligatorio para editar.");
        }
        if (!service.userExistsById(user.getId())) {
            return ResponseEntity.status(404).body("Error: No se encuentra un usuario con el ID " + user.getId());
        }
        User editedUser = service.editUser(user);
        return ResponseEntity.ok(editedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        if (service.userExistsById(id)) {
            service.deleteUser(id);
            return ResponseEntity.ok("Usuario con ID " + id + " eliminado con éxito.");
        } else {
            return ResponseEntity.status(404).body("No se encuentra un usuario con el ID " + id);
        }
    }
}