package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepositoryJpa extends JpaRepository<Hardware, Integer> {

    Hardware findByName(String name);
    boolean existsByName(String name);
}
