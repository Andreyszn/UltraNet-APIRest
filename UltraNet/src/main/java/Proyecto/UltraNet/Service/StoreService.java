package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Dto.StoreDto;
import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Model.Store;
import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Repository.StoreJpaRepository;
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

    public Store add(StoreDto storeDto) {
        User user = userService.findUserByEmail(storeDto.getUserEmail());
        Hardware hardware = hardwareService.findHardwareById(storeDto.getHardwareId());

        if (hardware.getQuantity() != 0 && hardware.getQuantity() >= storeDto.getQuantity() ) {
            hardware.setQuantity(hardware.getQuantity() - storeDto.getQuantity());
            hardwareService.putHardware(hardware);
            Store store = new Store();
            store.setUser(user);
            store.setHardware(hardware);
            store.setQuantity(storeDto.getQuantity());
            int sumPrice = storeDto.getQuantity()*hardware.getPrice();
            Double totalPrice = (double) sumPrice;
            store.setTotalPrice(totalPrice);
            store.setSaleDate(storeDto.getSaleDate());
            return storeRepository.save(store);
        }
        return null;
    }

    public Hardware findHardwareBysId(Integer id){
        Store store = findById(id);
        return store.getHardware();
    }

    public Hardware findHardwareById (Integer id){
        return hardwareService.findHardwareById(id);
    }

//    public Hardware findPickedHardware (StoreDto storeDto) {
//        Hardware hardware = hardwareService.findHardwareById(storeDto.getHardwareId());
//        return hardware;
//    }

    public void deleteCartItem(Integer storeId) {
        if (storeRepository.existsById(storeId)) {
            Store store = storeRepository.findById(storeId).get();
            Hardware hardware = hardwareService.findHardwareById(store.getHardware().getId());

            hardware.setQuantity(hardware.getQuantity() + store.getQuantity());
            hardwareService.putHardware(hardware);
            storeRepository.deleteById(storeId);
        }
    }

//    public List<Store> getCartByUser(Integer userId) {
//        User user = userService.findUserById(userId);
//        if (user == null) {
//            return null;
//        }
//        return storeRepository.findByUser(user);
//    }

    public Store findById(Integer id){
        Store store = new Store();
        store = storeRepository.findById(id).orElse(null);
        return store;
    }

    public String getInvoiceById(Integer storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);

        if (store != null) {
            User user = store.getUser();
            Hardware hardware = store.getHardware();

            String invoice = "------ UltraNet Invoice ------\n" +
                             "Invoice id: " + store.getId() + "\n" +
                             "Date: " + store.getSaleDate() + "\n\n" +

                             "Customer information:\n" +
                             "Name: " + user.getName() + "\n" +
                             "Email: " + user.getEmail() + "\n\n" +

                             "Products details:\n" +
                             "Name: " + hardware.getName() + "\n" +
                             "Type: " + hardware.getType() + "\n" +
                             "Brand: " + hardware.getBrand() + "\n" +
                             "Description: " + hardware.getDescription() + "\n\n" +

                             "Order summary:\n" +
                             "Quantity: " + store.getQuantity() + "\n" +
                             "Unit price: $" + hardware.getPrice() + "\n" +
                             "Total price: $" + store.getTotalPrice() + "\n" +
                             "----------------------------";
            return invoice;
        }
        return "Error, please verify the id.";
    }
}