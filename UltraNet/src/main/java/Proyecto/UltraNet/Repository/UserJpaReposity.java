package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaReposity extends JpaRepository <User, Integer> {

}
