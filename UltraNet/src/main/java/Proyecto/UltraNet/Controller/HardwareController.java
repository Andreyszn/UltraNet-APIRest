package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired
    HardwareService service;

    private ArrayList<Hardware> hardwares;

    @GetMapping
    public ArrayList<Hardware> getAll() { return service.getAll(); }

    @GetMapping("{id}")
    public Hardware getHardware(@PathVariable int id) { return service.searchHardware(id); }

    @PostMapping
    public Hardware postHardware(@RequestBody Hardware hardware) { return service.add(hardware); }

    @PutMapping
    public Hardware putHardware(@RequestBody Hardware hardware) { return service.updateHardware(hardware); }

    @DeleteMapping("{id}")
    public Hardware deleteHardware(@PathVariable int id) { return service.delete(id); }

    @PatchMapping
    public Hardware patchHardware(@RequestBody Hardware hardware) { return service.editHardware(hardware); }
}
