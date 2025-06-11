package Proyecto.UltraNet.Service;

import Proyecto.UltraNet.Model.Hardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Proyecto.UltraNet.Repository.HardwareRepository;

import java.util.ArrayList;


@Service
public class HardwareService {
    @Autowired
    HardwareRepository repository;

    public ArrayList<Hardware> getAll(){return repository.getAll();}

    public Hardware add(Hardware hardware){return repository.add(hardware);}

    public Hardware delete(int id){return repository.delete(id);}

    public Hardware searchHardware(int id){return repository.search(id);}

    public Hardware editHardware(Hardware hardware){return repository.edit(hardware);}

    public Hardware updateHardware(Hardware hardware){return repository.update(hardware);}

}
