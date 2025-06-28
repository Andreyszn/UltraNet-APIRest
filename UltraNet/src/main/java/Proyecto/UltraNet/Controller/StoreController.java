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
        if(!service.getAll().isEmpty() || service.getAll()==null) {
            return ResponseEntity.ok(service.getAll());
        }else{
            return ResponseEntity.status(404).body("No se encuentran compras registradas");
        }
    }

    @PostMapping
    public ResponseEntity<?> postStore(@RequestBody StoreDto storeDto){
        List<Hardware> listHardware = service.getListHardwareBuy(storeDto.getHardwareId());
        if (service.validationQuantity(listHardware, storeDto.getQuantity())) {
            return ResponseEntity.ok(service.add(storeDto));
        }
         return ResponseEntity.status(404).body("Error, no hay stock del item: \n" + listHardware);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        Store storeLocal = service.getStoreById(id);
        List<Integer> idHardwareList = service.getIdHardwareList(storeLocal.getHardware());
        List<Hardware> hardwareLocal = service.getListHardwareBuy(idHardwareList);
        service.deleteCartItem(id);
        return ResponseEntity.ok(hardwareLocal);
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<String> getFactura(@PathVariable Integer id) {
        String invoice = service.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

}
