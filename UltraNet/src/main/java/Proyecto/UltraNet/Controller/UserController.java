package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Dto.LoginDto;
import Proyecto.UltraNet.Dto.UserDto;
import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto) {
        if (service.userExistsByEmail(userDto.getEmail())) {
            return ResponseEntity.status(400).body("Error: Ya existe un usuario con el email " + userDto.getEmail());
        }

        if(service.isInvalidType(userDto.getType())){
            return ResponseEntity.status(400).body("Error: El espacio type debe ser igual a client o Administrador");
        }

        User createdUser = service.addUser(userDto);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto) {
        if (userDto.getId() == null){
            return ResponseEntity.status(400).body("Error: El ID del usuario es obligatorio para actualizar.");
        }

        if (!service.userExistsById(userDto.getId())) {
            return ResponseEntity.status(404).body("Error: No se encuentra un usuario con el ID " + userDto.getId());
        }

        if(service.isInvalidType(userDto.getType())){
            return ResponseEntity.status(400).body("Error: El espacio type debe rellenado con Client o Administrator");
        }

        User updatedUser = service.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping
    public ResponseEntity<?> editUser (@RequestBody User user){
        if (user.getId() == null) {
            return ResponseEntity.status(400).body("Error: El ID del usuario es obligatorio para editar.");
        }
        if (!service.userExistsById(user.getId())) {
            return ResponseEntity.status(404).body("Error: No se encuentra un usuario con el ID " + user.getId());
        }
        if(service.isInvalidType(user.getType())){
            return ResponseEntity.status(400).body("Error: El espacio type debe rellenado con Client o Administrator");
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

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        if(service.userActive().isActive()){
            return ResponseEntity.ok("Error, Ya el usuario "+service.userActive().getName()+" se encuentra activo");
        }
        User userlogin = service.loginByEmail(loginDto);
        if(loginDto.getPasswordUser().equals(userlogin.getPassword())){
            return ResponseEntity.ok("Bienvenido "+userlogin.getName());
        }
        return ResponseEntity.status(404).body("Error, no se ha encontrado usuario registrado.");
    }

    @GetMapping("/exit")
    public ResponseEntity<?> exitLoginUser(){
        if(!service.userActive().isActive()){
            return ResponseEntity.ok("Error, no se encuentra ningun usuario activo");
        }
        service.exitLogin(service.userActive());
        return ResponseEntity.ok("El usuario "+service.userActive().getName()+" ha cerrado sesion");
    }
}