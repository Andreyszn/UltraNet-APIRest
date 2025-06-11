package Proyecto.UltraNet.Service;

<<<<<<< HEAD
import UltraNet.Model.Hardware;
import UltraNet.Model.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import UltraNet.Repository.HardwareRepository;
=======
import Proyecto.UltraNet.Model.Hardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Proyecto.UltraNet.Repository.HardwareRepository;

import java.util.ArrayList;
>>>>>>> Charlie


@Service
public class HardwareService {
    @Autowired
    HardwareRepository repository;

    public ArrayList<Hardware> getAll(){return repository.getAll();}
<<<<<<< HEAD
    public Hardware add(Hardware hardware){return repository.add(harware);}
    public Hardware edit(Hardware hardware){return repository.edit(hardware);}
    public Hardware update(Hardware hardware){return repository.update(hardware);}
    public Hardware delete(Identifiable id){return repository.delete(id.getId());}
    public Hardware search(Identifiable id){return repository.search(id.getId());}
=======

    public Hardware add(Hardware hardware){return repository.add(hardware);}

    public Hardware delete(int id){return repository.delete(id);}

    public Hardware searchHardware(int id){return repository.search(id);}

    public Hardware editHardware(Hardware hardware){return repository.edit(hardware);}

    public Hardware updateHardware(Hardware hardware){return repository.update(hardware);}
>>>>>>> Charlie

}
