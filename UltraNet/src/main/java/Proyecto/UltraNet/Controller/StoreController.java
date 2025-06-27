package Proyecto.UltraNet.Controller;

import Proyecto.UltraNet.Dto.StoreDto;
import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Model.Store;
import Proyecto.UltraNet.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(service.add(storeDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        StoreDto storeDto = str
        Hardware local = service.findPickedHardware(storeDto);
        service.deleteCartItem(id);
        return ResponseEntity.ok("hardware eliminado " + local);
    }
}
