package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Model.Hardware;
import Proyecto.UltraNet.Model.Store;
import Proyecto.UltraNet.Model.User;
import Proyecto.UltraNet.Repository.HardwareRepositoryJpa;
import Proyecto.UltraNet.Repository.StoreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreJpaRepository storeJpaRepository;

    @Autowired
    private HardwareRepositoryJpa hardwareRepositoryJpa;

    //Add item to cart
    //Remove from cart
    //get cart?
    //clean the cart

    //then the controller, should be easy enough
}