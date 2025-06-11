package Proyecto.UltraNet.Model;


//@Entity
public class Hardware implements Identifiable{
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name, description, type, brand, conection, power;
    private Integer quantity, price;
    //motherboard
    private string cpuPort, pciePort,ramPort,storagePort;

    public Hardware() {
    }

    public Hardware(Integer id, String name, String description, String type, String brand, String conection, String power, Integer quantity, Integer price, string cpuPort, string pciePort, string ramPort, string storagePort) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.brand = brand;
        this.conection = conection;
        this.power = power;
        this.quantity = quantity;
        this.price = price;
        this.cpuPort = cpuPort;
        this.pciePort = pciePort;
        this.ramPort = ramPort;
        this.storagePort = storagePort;
    }

    //@Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getConection() {
        return conection;
    }

    public void setConection(String conection) {
        this.conection = conection;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public string getCpuPort() {
        return cpuPort;
    }

    public void setCpuPort(string cpuPort) {
        this.cpuPort = cpuPort;
    }

    public string getPciePort() {
        return pciePort;
    }

    public void setPciePort(string pciePort) {
        this.pciePort = pciePort;
    }

    public string getRamPort() {
        return ramPort;
    }

    public void setRamPort(string ramPort) {
        this.ramPort = ramPort;
    }

    public string getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(string storagePort) {
        this.storagePort = storagePort;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Hardware{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", conection='" + conection + '\'' +
                ", power='" + power + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", cpuPort=" + cpuPort +
                ", pciePort=" + pciePort +
                ", ramPort=" + ramPort +
                ", storagePort=" + storagePort +
                '}';
    }
}
