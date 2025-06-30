package Proyecto.UltraNet.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class UserDto {
    private Integer id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String name;

    @Email(message = "El correo no cumple la estructura")
    @NotBlank(message = "El correo no puede quedar en blanco")
    private String email;

    @NotBlank(message = "El tipo no puede estar en blanco")
    private String type;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un símbolo")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
