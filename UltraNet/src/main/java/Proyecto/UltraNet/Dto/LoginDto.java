package Proyecto.UltraNet.Dto;

public class LoginDto {
    private String emailUser;
    private String passwordUser;

    public LoginDto() {
    }

    public LoginDto(String emailUser, String passwordUser) {
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
}
