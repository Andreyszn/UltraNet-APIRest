package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hardwares")
public class HardwareController {

    @Autowired
    HardwareService service;

    @GetMapping
    public ResponseEntity<List<Hardware>> getAll() {
        List<Hardware> hardwareList = service.getAll();
        return ResponseEntity.ok(hardwareList);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHardware(@PathVariable Integer id) {
        Hardware hardware = service.searchHardware(id);
        if (hardware == null) {
            return ResponseEntity.status(404).body("No se encontró hardware con ID " + id);
        }
        return ResponseEntity.ok(hardware);
    }

    @PostMapping
    public ResponseEntity<?> postHardware(@RequestBody Hardware hardware) {
        Hardware created = service.addHardware(hardware);

        if(!service.typeIsCorrect(hardware.getType())){return ResponseEntity.status(404).body("Falta un type válido");}

        else{
            if(service.typeIsMotherBoard(hardware.getType())){

                if(!service.motherGraphics(hardware.getPciePort())){return ResponseEntity.status(404).body("Falta un puerto pcie válido");}
                if(!service.motherStorage(hardware.getStoragePort())){return ResponseEntity.status(404).body("Falta un almacenamiento válido");}
                if(!service.motherRam(hardware.getRamPort())){return ResponseEntity.status(404).body("Falta una RAM valida");}
                if( !(service.motherCpuAMD(hardware.getCpuPort())) && !(service.motherCpuIntel(hardware.getCpuPort())) ){
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
        if (hardware.getId() == null) {
            return ResponseEntity.status(400).body("El ID es obligatorio para actualizar hardware.");
        }
        if (!service.existsById(hardware.getId())) {
            return ResponseEntity.status(404).body("No se encontró hardware con ID " + hardware.getId());
        }
        if (service.existByName(hardware.getName())){
            return ResponseEntity.status(400).body("cambie el nombre que esta repetido");
        }
        if(!service.typeIsCorrect(hardware.getType())){return ResponseEntity.status(404).body("Falta un type válido");}
        Hardware updated = service.putHardware(hardware);

        if(service.typeIsMotherBoard(hardware.getType())){

            if(!service.motherGraphics(hardware.getPciePort())){return ResponseEntity.status(404).body("Falta un puerto pcie válido");}
            if(!service.motherStorage(hardware.getStoragePort())){return ResponseEntity.status(404).body("Falta un almacenamiento válido");}
            if(!service.motherRam(hardware.getRamPort())){return ResponseEntity.status(404).body("Falta una RAM valida");}
            if( !(service.motherCpuAMD(hardware.getCpuPort())) && !(service.motherCpuIntel(hardware.getCpuPort())) ){
                return ResponseEntity.status(404).body("Falta un socket válido");
            }

            return ResponseEntity.status(201).body(updated);
        }

        return ResponseEntity.ok(updated);
    }

    @PatchMapping
    public ResponseEntity<?> patchHardware(@RequestBody Hardware hardware) {
        if (hardware.getId() == null) {
            return ResponseEntity.status(400).body("El ID es obligatorio para editar hardware.");
        }
        if (!service.existsById(hardware.getId())) {
            return ResponseEntity.status(404).body("No se encontró hardware con ID " + hardware.getId());
        }
//        if(!service.typeIsCorrect(hardware.getType())){return ResponseEntity.status(404).body("Falta un type válido");}
        Hardware patched = service.patchHardware(hardware);
        if(!service.existByName(hardware.getName())){ return ResponseEntity.status(400).body("Verifique que el nombre no este repetido");}else{
        return ResponseEntity.ok(patched);}
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHardware(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Hardware con ID " + id + " eliminado con éxito.");
        }
        return ResponseEntity.status(404).body("No se encontró hardware con ID " + id);
    }
}