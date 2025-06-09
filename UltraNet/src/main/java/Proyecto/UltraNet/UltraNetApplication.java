package Proyecto.UltraNet;

import Proyecto.UltraNet.Controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UltraNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(UltraNetApplication.class, args);
		new UserController();
	}
}
