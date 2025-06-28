package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Repository.HardwareRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
      if(!existByName(hardware.getName())) {
          if (typeIsCorrect(hardware.getType())) {
              if (typeIsMotherBoard(hardware.getType())) {
                  if (motherGraphics(hardware.getPciePort())) {
                      if (motherRam(hardware.getRamPort())) {
                          if (motherStorage(hardware.getStoragePort())) {
                              if ((motherCpuAMD(hardware.getCpuPort()) || (motherCpuIntel(hardware.getCpuPort())))) {
                                  return repository.save(hardware);
                              }

                          }

                      }

                  }

              } else {
                  return repository.save(hardware);
              }
          }
          return null;
      }
      return null;
        //return repository.save(hardware);
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
        Hardware hardwareBd = repository.findById(hardware.getId()).get();

        if (hardware.getName() != null) {
            if(existByName(hardware.getName())){
                ; }else{
            hardwareBd.setName(hardware.getName());  }
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
        if (hardware.getPower() != null && hardware.getPower() != 0) {
            hardwareBd.setPower(hardware.getPower());
        }
        if (hardware.getQuantity() != null && hardware.getQuantity() != 0) {
            hardwareBd.setQuantity(hardware.getQuantity());
        }
        if (hardware.getPrice() != null && hardware.getPrice() != 0) {
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
        //if(typeIsCorrect(hardware.getType())){
        return repository.save(hardwareBd);
        //}

        //return null;
    }

    public Hardware putHardware(Hardware hardware) {
//        if(typeIsCorrect(hardware.getType())){

            return repository.save(hardware);
//        }
//        return null;
    //    return repository.save(hardware);
    }

    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    // compatibilidad entre motherboard y cpu de Intel
    public boolean motherCpuIntel(String cpuPort) {
        if(cpuPort.equalsIgnoreCase("LGA 1155")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("LGA 1150")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("LGA 1151")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("LGA 2066")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("LGA 1200")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("LGA 1700")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("LGA 1851")){
            return true;
        }

        else{
            return false;
        }
    }

    // compatibilidad entre motherboard y cpu de AMD
    public boolean motherCpuAMD(String cpuPort) {
        if(cpuPort.equalsIgnoreCase("AM5")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("AM4")){
            return true;
        }
        if(cpuPort.equalsIgnoreCase("AM3")) {
            return true;
        }else{
            return false;
        }
    }

    //misma version de Pcie entre motherboard y tarjeta grafica
    public boolean motherGraphics(String pciePort){
        if(pciePort.equalsIgnoreCase("Pcie 5.0")){
            return true;
        }
        if(pciePort.equalsIgnoreCase("Pcie 4.0")){
            return true;
        }
        if(pciePort.equalsIgnoreCase("Pcie 3.0")){
            return true;
        }
        else {
            return false;
        }
    }

    //Compatibilidad entre motherboard y memoria RAM
    public boolean motherRam(String ramPort){
        if(ramPort.equalsIgnoreCase("DDR5")){
            return true;
        }
        if(ramPort.equalsIgnoreCase("DDR4")){
            return true;
        }
        if(ramPort.equalsIgnoreCase("DDR3")){
            return true;
        }
        else {
            return false;
        }
    }
    //Compatibilidad entre motherboard y almacenamiento
    public boolean motherStorage(String storagePort){
        if(storagePort.equalsIgnoreCase("pcie 5.0")){
            return true;
        }
        if(storagePort.equalsIgnoreCase("Pcie 4.0")){
            return true;
        }
        if(storagePort.equalsIgnoreCase("pcie 3.0")){
            return true;
        }
        if(storagePort.equalsIgnoreCase("SATA 3")){
            return true;
        }
        if(storagePort.equalsIgnoreCase("SATA")){
            return true;
        }
        if(storagePort.equalsIgnoreCase("NVMe")){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean typeIsCorrect(String type){
        if(type.equalsIgnoreCase("MotherBoard")){return true;}
        if(type.equalsIgnoreCase("CPU")){return true;}
        if(type.equalsIgnoreCase("GPU")){return true;}
        if(type.equalsIgnoreCase("RAM")){return true;}
        if(type.equalsIgnoreCase("Storage")){return true;}
        if(type.equalsIgnoreCase("Power Supply")){return true;}
        if(type.equalsIgnoreCase("Gadget")){return true;}
        else{return false;}
    }

    public boolean typeIsMotherBoard(String type){
        if(type.equalsIgnoreCase("MotherBoard")){return true;}
        else{return false;}
    }

//    public boolean compatibility(boolean great){
//        if(great = true){
//            return true;
//        }else{
//            return false;
//        }
//
//    }

    public boolean existByName(String name) {
        List<Hardware> hardwares = repository.findAll();
        for (Hardware hardware : hardwares) {
            if (hardware.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Hardware findHardwareById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}









