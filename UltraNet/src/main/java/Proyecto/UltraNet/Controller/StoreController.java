package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Dto.StoreDto;
import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    StoreService service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> postStore(@RequestBody StoreDto storeDto){
        Hardware hardware = service.findHardwareById(storeDto.getHardwareId());
        if (hardware.getQuantity()!=0) {
            return ResponseEntity.ok(service.add(storeDto));
        }
         return ResponseEntity.status(404).body("Error, no hay stock del item: \n" + hardware);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        Hardware hardwareLocal = service.findHardwareBysId(id);
        service.deleteCartItem(id);
        return ResponseEntity.ok(hardwareLocal);
    }
}
