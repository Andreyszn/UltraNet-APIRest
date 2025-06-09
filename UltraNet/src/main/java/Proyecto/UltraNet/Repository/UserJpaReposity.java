package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaReposity extends JpaRepository <User, Integer> {

}
