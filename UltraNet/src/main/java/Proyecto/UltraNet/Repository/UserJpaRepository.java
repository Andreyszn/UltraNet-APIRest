package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository <User, Integer> {

}
