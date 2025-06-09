package Proyecto.UltraNet.Controller;

@RestController
@RequestMapping("/hardware")
public class HardwareController {

    @Autowired
    HardwareService service;

    private ArrayList<Hardware> hardwares;


    @getMapping
    public ArrayList<Hardware> getAll(){return service.getAll();}

    @GetMapping("{id}")
    public Hardware getHardware(@PathVariable int id){

        return null;
    }

    @PostMapping
    public Hardware postSpace(@RequestBody Hardware hardware){


        return null;
    }

    @PutMapping
    public Hardware putSpace(@RequestBody Hardware hardware){


        return null;
    }

    @DeleteMapping("{id}")
    public Hardware deleteSpace(@PathVariable int id){


        return null;
    }

    @PatchMapping
    public Hardware verifySpace(@RequestBody Hardware hardware){


        return null;
    }

}
