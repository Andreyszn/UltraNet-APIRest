package Proyecto.UltraNet.Service;

import UltraNet.Model.Hardware;
import UltraNet.Model.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import UltraNet.Repository.HardwareRepository;


@Service
public class HardwareService {
    @Autowired
    HardwareRepository repository;

    public ArrayList<Hardware> getAll(){return repository.getAll();}
    public Hardware add(Hardware hardware){return repository.add(harware);}
    public Hardware edit(Hardware hardware){return repository.edit(hardware);}
    public Hardware update(Hardware hardware){return repository.update(hardware);}
    public Hardware delete(Identifiable id){return repository.delete(id.getId());}
    public Hardware search(Identifiable id){return repository.search(id.getId());}

}
