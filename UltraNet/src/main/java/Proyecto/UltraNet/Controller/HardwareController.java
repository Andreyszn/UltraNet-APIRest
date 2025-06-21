package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/hardwares")
public class HardwareController {

    @Autowired
    HardwareService service;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(201).body(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getHardware(@PathVariable Integer id) {
        return ResponseEntity.ok(service.searchHardware(id));
    }

    @PostMapping
    public ResponseEntity<?> postHardware(@RequestBody Hardware hardware) {
        return ResponseEntity.ok(service.addHardware(hardware));
    }

    @PutMapping
    public ResponseEntity<?> putHardware(@RequestBody Hardware hardware) {
        return ResponseEntity.ok(putHardware(hardware));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHardware(@PathVariable Integer id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PatchMapping
    public ResponseEntity<?> patchHardware(@RequestBody Hardware hardware) {
        return ResponseEntity.status(201).body(patchHardware(hardware));
    }
}
