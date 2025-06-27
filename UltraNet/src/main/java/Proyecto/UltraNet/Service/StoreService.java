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

    public StoreDto findStoreById (Integer id) {
        return storeRepository.findById(id);
    }

    public Hardware findHardwareById (Integer id){
        return hardwareService.findHardwareById(id);
    }

    public Hardware findPickedHardware (StoreDto storeDto) {
        Hardware hardware = hardwareService.findHardwareById(storeDto.getHardwareId());
        return hardware;
    }

    public void deleteCartItem(Integer storeId) {
        if (storeRepository.existsById(storeId)) {
            Store store = storeRepository.findById(storeId).get();
            Hardware hardware = hardwareService.findHardwareById(store.getHardware().getId());

            // Esto devuelve el stock?
            hardware.setQuantity(hardware.getQuantity() + store.getQuantity());
            hardwareService.putHardware(hardware);
            storeRepository.deleteById(storeId);
        }
    }

    public List<Store> getCartByUser(Integer userId) {
        User user = userService.findUserById(userId);
        if (user == null) {
            return null;
        }
        return storeRepository.findByUser(user);
    }



}