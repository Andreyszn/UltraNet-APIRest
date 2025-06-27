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
        if (storeDto.getQuiantity()!=0) {
            return ResponseEntity.ok(service.add(storeDto));
        }else{
            return ResponseEntity.ok("No hay stock disponible para esta compra");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        Hardware hardwareLocal = service.findHardwareBysId(id);
        service.deleteCartItem(id);
        return ResponseEntity.ok(hardwareLocal);
    }
}
