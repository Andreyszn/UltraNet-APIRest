package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Model.Store;
import Proyecto.UltraNet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface StoreJpaRepository extends JpaRepository <Store, Integer> {
    List<Store> findByUser(User user);
    Optional<Store> findByUserAndHardware(User user, Hardware hardware);
}
