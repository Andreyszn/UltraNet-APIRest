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
    public ResponseEntity<Hardware> postHardware(@RequestBody Hardware hardware) {
        Hardware created = service.addHardware(hardware);
        if(created == null){return ResponseEntity.status(400).build();}
        else{
            if(service.typeIsMotherBoard(hardware.getType())){
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
        Hardware updated = service.putHardware(hardware);

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

        Hardware patched = service.patchHardware(hardware);
        if(patched == null){ return ResponseEntity.status(400).body("Verifique que el nombre no este repetido");}else{
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