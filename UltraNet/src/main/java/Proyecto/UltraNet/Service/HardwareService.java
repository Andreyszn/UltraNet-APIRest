package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Repository.HardwareRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareService {

    @Autowired
    HardwareRepositoryJpa repository;

    public List<Hardware> getAll() {
        return repository.findAll();
    }

    public Hardware addHardware(Hardware hardware) {
        return repository.save(hardware);
    }

    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Hardware searchHardware(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Hardware patchHardware(Hardware hardware) {
        Hardware hardwareBd = repository.findById(hardware.getId()).orElse(null);
        if (hardwareBd == null) {
            return null; // o lanzar excepción
        }

        if (hardware.getName() != null) {
            hardwareBd.setName(hardware.getName());
        }
        if (hardware.getDescription() != null) {
            hardwareBd.setDescription(hardware.getDescription());
        }
        if (hardware.getType() != null) {
            hardwareBd.setType(hardware.getType());
        }
        if (hardware.getBrand() != null) {
            hardwareBd.setBrand(hardware.getBrand());
        }
        if (hardware.getConection() != null) {
            hardwareBd.setConection(hardware.getConection());
        }
        if (hardware.getPower() != 0) {
            hardwareBd.setPower(hardware.getPower());
        }
        if (hardware.getQuantity() != 0) {
            hardwareBd.setQuantity(hardware.getQuantity());
        }
        if (hardware.getPrice() != 0) {
            hardwareBd.setPrice(hardware.getPrice());
        }
        if (hardware.getCpuPort() != null) {
            hardwareBd.setCpuPort(hardware.getCpuPort());
        }
        if (hardware.getPciePort() != null) {
            hardwareBd.setPciePort(hardware.getPciePort());
        }
        if (hardware.getRamPort() != null) {
            hardwareBd.setRamPort(hardware.getRamPort());
        }
        if (hardware.getStoragePort() != null) {
            hardwareBd.setStoragePort(hardware.getStoragePort());
        }

        return repository.save(hardwareBd);
    }

    public Hardware putHardware(Hardware hardware) {
        return repository.save(hardware);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }
}