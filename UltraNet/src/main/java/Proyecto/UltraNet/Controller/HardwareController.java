package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Service.HardwareService;
import Proyecto.UltraNet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hardwares")
public class HardwareController {

    @Autowired
    UserService userService;

    @Autowired
    HardwareService hardwareService;

    @GetMapping
    public ResponseEntity<List<Hardware>> getAll() {
        List<Hardware> hardwareList = hardwareService.getAll();
        return ResponseEntity.ok(hardwareList);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHardware(@PathVariable Integer id) {
        Hardware hardware = hardwareService.searchHardware(id);
        if (hardware == null) {
            return ResponseEntity.status(404).body("No se encontró hardware con ID " + id);
        }
        return ResponseEntity.ok(hardware);
    }

    @PostMapping
    public ResponseEntity<?> postHardware(@RequestBody Hardware hardware) {
        if(!userService.userActive().isActive()){
            return ResponseEntity.status(404).body("Error, no se encuentra usuario activo para realizar esta accion");
        }
        if(!userService.userActive().getType().equalsIgnoreCase("Administrador")){
            return ResponseEntity.ok("El tipo de usuario no tiene permiso para realizar esta acción");
        }
        Hardware created = hardwareService.addHardware(hardware);

        if(!hardwareService.typeIsCorrect(hardware.getType())){return ResponseEntity.status(404).body("Falta un type válido");}

        else{
            if(hardwareService.typeIsMotherBoard(hardware.getType())){

                if(!hardwareService.motherGraphics(hardware.getPciePort())){return ResponseEntity.status(404).body("Falta un puerto pcie válido");}
                if(!hardwareService.motherStorage(hardware.getStoragePort())){return ResponseEntity.status(404).body("Falta un almacenamiento válido");}
                if(!hardwareService.motherRam(hardware.getRamPort())){return ResponseEntity.status(404).body("Falta una RAM valida");}
                if( !(hardwareService.motherCpuAMD(hardware.getCpuPort())) && !(hardwareService.motherCpuIntel(hardware.getCpuPort())) ){
                    return ResponseEntity.status(404).body("Falta un socket válido");
                }

                if(created == null){return ResponseEntity.status(404).body("Nombre repetido cambialo la chucha");}

                return ResponseEntity.status(201).body(created);
            }
        return ResponseEntity.status(201).body(created);
        }
    }

    @PutMapping
    public ResponseEntity<?> putHardware(@RequestBody Hardware hardware) {
        if(!userService.userActive().isActive()){
            return ResponseEntity.status(404).body("Error, no se encuentra usuario activo para realizar esta accion");
        }
        if(!userService.userActive().getType().equalsIgnoreCase("Administrador")){
            return ResponseEntity.ok("El tipo de usuario no tiene permiso para realizar esta acción");
        }
        if (hardware.getId() == null) {
            return ResponseEntity.status(400).body("El ID es obligatorio para actualizar hardware.");
        }
        if (!hardwareService.existsById(hardware.getId())) {
            return ResponseEntity.status(404).body("No se encontró hardware con ID " + hardware.getId());
        }
        if (hardwareService.existByName(hardware.getName())){
            return ResponseEntity.status(400).body("cambie el nombre que esta repetido");
        }
        if(!hardwareService.typeIsCorrect(hardware.getType())){return ResponseEntity.status(404).body("Falta un type válido");}
        Hardware updated = hardwareService.putHardware(hardware);

        if(hardwareService.typeIsMotherBoard(hardware.getType())){

            if(!hardwareService.motherGraphics(hardware.getPciePort())){return ResponseEntity.status(404).body("Falta un puerto pcie válido");}
            if(!hardwareService.motherStorage(hardware.getStoragePort())){return ResponseEntity.status(404).body("Falta un almacenamiento válido");}
            if(!hardwareService.motherRam(hardware.getRamPort())){return ResponseEntity.status(404).body("Falta una RAM valida");}
            if( !(hardwareService.motherCpuAMD(hardware.getCpuPort())) && !(hardwareService.motherCpuIntel(hardware.getCpuPort())) ){
                return ResponseEntity.status(404).body("Falta un socket válido");
            }

            return ResponseEntity.status(201).body(updated);
        }

        return ResponseEntity.ok(updated);
    }

    @PatchMapping
    public ResponseEntity<?> patchHardware(@RequestBody Hardware hardware) {
        if(!userService.userActive().isActive()){
            return ResponseEntity.status(404).body("Error, no se encuentra usuario activo para realizar esta accion");
        }
        if(!userService.userActive().getType().equalsIgnoreCase("Administrador")){
            return ResponseEntity.ok("El tipo de usuario no tiene permiso para realizar esta acción");
        }
        if (hardware.getId() == null) {
            return ResponseEntity.status(400).body("El ID es obligatorio para editar hardware.");
        }
        if (!hardwareService.existsById(hardware.getId())) {
            return ResponseEntity.status(404).body("No se encontró hardware con ID " + hardware.getId());
        }
        Hardware patched = hardwareService.patchHardware(hardware);
        if(!hardwareService.existByName(hardware.getName())){ return ResponseEntity.status(400).body("Verifique que el nombre no este repetido");}else{
        return ResponseEntity.ok(patched);}
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHardware(@PathVariable Integer id) {
        if(!userService.userActive().isActive()){
            return ResponseEntity.status(404).body("Error, no se encuentra usuario activo para realizar esta accion");
        }
        if(!userService.userActive().getType().equalsIgnoreCase("Administrador")){
            return ResponseEntity.ok("El tipo de usuario no tiene permiso para realizar esta acción");
        }
        boolean deleted = hardwareService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Hardware con ID " + id + " eliminado con éxito.");
        }
        return ResponseEntity.status(404).body("No se encontró hardware con ID " + id);
    }
}