package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Dto.StoreDto;
import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Model.Store;
import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Repository.StoreJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public boolean existsById(Integer id){
        return storeRepository.existsById(id);
    }

    public List<Store> getAll(){
        return storeRepository.findAll();
    }

    public Store add(StoreDto storeDto) {
        User user = userService.findUserByEmail(storeDto.getUserEmail());
        List<Hardware> hardware = getListHardwareBuy(storeDto.getHardwareId());
        List<Integer> quantities = storeDto.getQuantity();

            decreaseQuantity(hardware, quantities);
            Store store = new Store();
            store.setUser(user);
            store.setHardware(hardware);
            store.setQuantity(storeDto.getQuantity());
            int sumPrice = getTotalPrice(hardware, quantities);
            Double totalPrice = (double) sumPrice;
            store.setTotalPrice(totalPrice);
            store.setSaleDate(storeDto.getSaleDate());
            return storeRepository.save(store);
    }

    public List<Hardware> getListHardwareBuy(List<Integer> buyList){
        if(buyList==null || buyList.isEmpty()){ return new ArrayList<>();}

        return hardwareService.findAllById(buyList);
    }

    public boolean validationQuantity(List<Hardware> listHardware, List<Integer> listQuantities){
        if(listHardware.size()!=listQuantities.size()){
            return false;
        }
        for(int element=0;element<listHardware.size();element++){
            if(listHardware.get(element).getQuantity()<listQuantities.get(element)){
                return false;
            }
        }
        return true;
    }

    public void increaseQuantity(List<Hardware> listHardware, List<Integer> listQuantities){
        for(int element=0; element<listHardware.size();element++){
            Hardware hardware = listHardware.get(element);
            Integer newQuantity = hardware.getQuantity()+listQuantities.get(element);
            hardware.setQuantity(newQuantity);
            hardwareService.putHardware(hardware);
        }
    }

    public void decreaseQuantity(List<Hardware> listHardware, List<Integer> listQuantities){
        for(int element=0; element<listHardware.size();element++){
            Hardware hardware = listHardware.get(element);
            Integer newQuantity = hardware.getQuantity()-listQuantities.get(element);
            hardware.setQuantity(newQuantity);
            hardwareService.putHardware(hardware);
        }
    }

    public int getTotalPrice(List<Hardware> listHardware, List<Integer> listQuantities){
        int totalPrice=0;
        for(int element=0; element<listHardware.size();element++){
            totalPrice+=listHardware.get(element).getPrice()*listQuantities.get(element);
        }
        return totalPrice;
    }

    public Store getStoreById(Integer id){
        return storeRepository.findById(id).orElse(null);
    }

    public List<Integer> getIdHardwareList(List<Hardware> listBuy){
        if(listBuy.isEmpty()){
            return new ArrayList<>();
        }
        List<Integer> listId= new ArrayList<>();
        for(int element=0; element<listBuy.size();element++){
            listId.add(listBuy.get(element).getId());
        }
        return listId;
    }

    public Hardware findHardwareById (Integer id){
        return hardwareService.findHardwareById(id);
    }

    public List<Hardware> getAllHardware(){
        return hardwareService.getAll();
    }

    public void deleteCartItem(Integer storeId) {
        if (storeRepository.existsById(storeId)) {
            Store store = storeRepository.findById(storeId).get();

            List<Integer> idHardware = getIdHardwareList(store.getHardware());
            List<Hardware> hardwares = getListHardwareBuy(idHardware);
            List<Integer> quantities = store.getQuantity();

            increaseQuantity(hardwares, quantities);
            storeRepository.deleteById(storeId);
        }
    }

    public Store findById(Integer id){
        return storeRepository.findById(id).orElse(null);
    }

    public String getInvoiceById(Integer storeId) {
        Store store = storeRepository.findById(storeId).orElse(null);
            User user = store.getUser();
            List<Hardware> hardwareList = store.getHardware();
            List<Integer> quantitiesList = store.getQuantity();

            String invoice = "------ UltraNet Invoice ------\n" +
                             "Invoice id: " + store.getId() + "\n" +
                             "Date: " + store.getSaleDate() + "\n\n" +

                             "Customer information:\n" +
                             "Name: " + user.getName() + "\n" +
                             "Email: " + user.getEmail() + "\n\n";

            for(int element=0; element<hardwareList.size();element++) {
                Hardware hardware = hardwareList.get(element);
                Integer quantity = quantitiesList.get(element);
                int subTotal = hardware.getPrice()*quantity;
                invoice +=
                        "Products details:\n" +
                        "Name: " + hardware.getName() + "\n" +
                        "Type: " + hardware.getType() + "\n" +
                        "Brand: " + hardware.getBrand() + "\n" +
                        "Description: " + hardware.getDescription() + "\n\n" +

                        "Order summary:\n" +
                        "Quantity: " + quantity + "\n" +
                        "Unit price: ₡" + hardware.getPrice() + "\n" +
                        "Sub Total price: ₡" + subTotal + "\n\n";
            }
            invoice +=
                    "Final Price: ₡"+store.getTotalPrice()+"\n"+
                            "----------------------------";

            return invoice;
    }
}