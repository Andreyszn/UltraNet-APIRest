package Proyecto.UltraNet.Repository;

import UltraNet.Model.Hardware;
import org.springframework.stereotype.Repository;

@Repository
public class HardwareRepository extends CRUDMemory<Hardware>{

    public HardwareRepository() {
        data.add(new Hardware(1,"rtx","grafica","Tarjeta de Video","Nvidea","Pcie 5.0","300 watts",10,40000,"--","--","--","--"))
    }

    @Override
    Public Hardware edit(Hardware hardware) {
        for(Hardware element : data) {
            if (element.getId().intValue() == hardware.getId().intValue()) {
//                if (space.getId() != 0) {
//                    element.setId(space.getId());
//                }
                if (hardware.getName() != null) {
                    element.setName(hardware.getName());
                }
                if (hardware.getDescription() != null) {
                    element.setDescription(hardware.getDescription());
                }
                if (hardware.getType() != null) {
                    element.setType(hardware.getType());
                }
                if (hardware.getBrand() != null) {
                    element.setBrand(hardware.getBrand());
                }
                if (hardware.getConection() != null) {
                    element.setConection(hardware.getConection());
                }
                if (hardware.getPower() != null) {
                    element.setPower(hardware.getPower());
                }
                if (hardware.getQuantity() != 0) {
                    element.setQuantity(hardware.getQuantity());
                }
                if (hardware.getPrice() != 0) {
                    element.setPrice(hardware.getPrice());
                }
                if (hardware.getCpuPort() != null) {
                    element.setCpuPort(hardware.getCpuPort());
                }
                if (hardware.getPciePort() != null) {
                    element.setPciePort(hardware.getPciePort());
                }
                if (hardware.getRamPort() != null) {
                    element.setRamPort(hardware.getRamPort());
                }
                if (hardware.getStoragePort() != null) {
                    element.setStoragePort(hardware.getStoragePort());
                }
                return element;

            }
        }
        return hardware;


    }

}
