package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Dto.StoreDto;
import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Model.Store;
import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Repository.HardwareRepositoryJpa;
import Proyecto.UltraNet.Repository.StoreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

//    @Autowired
//    private StoreJpaRepository storeJpaRepository;
//
//    @Autowired
//    private HardwareRepositoryJpa hardwareRepositoryJpa;

    private StoreJpaRepository storeRepository;
    private UserService userService;
    private HardwareService hardwareService;

    public StoreService(StoreJpaRepository storeRepository, UserService userService, HardwareService hardwareService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
        this.hardwareService = hardwareService;
    }

    public List<Store> getAll(){
        return storeRepository.findAll();
    }

    public Store add(StoreDto storeDto){
        User user = userService.findUserByEmail(storeDto.getUserEmail());
        Hardware hardware = hardwareService.findHardwareById(storeDto.getHardwareId());
        Store store = new Store();
        store.setUser(user);
        store.setHardware(hardware);
        store.setQuantity(storeDto.getQuiantity());
        store.setTotalPrice(storeDto.getTotalPrice());
        store.setSaleDate(storeDto.getSaleDate());
        return storeRepository.save(store);
    }

    //Add item to cart
    //Remove from cart
    //get cart?
    //clean the cart

    //then the controller, should be easy enough
}