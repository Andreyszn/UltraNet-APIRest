package Proyecto.UltraNet.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hardware implements Identifiable{
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name, description, type, brand, conection;
    private Integer quantity, price, power;
    //motherboard
    private String cpuPort, pciePort,ramPort,storagePort;

    public Hardware() {
    }

    public Hardware(Integer id, String name, String description, String type, String brand, String conection, Integer power, Integer quantity, Integer price, String cpuPort, String pciePort, String ramPort, String storagePort) {
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

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
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

    public String getCpuPort() {
        return cpuPort;
    }

    public void setCpuPort(String cpuPort) {
        this.cpuPort = cpuPort;
    }

    public String getPciePort() {
        return pciePort;
    }

    public void setPciePort(String pciePort) {
        this.pciePort = pciePort;
    }

    public String getRamPort() {
        return ramPort;
    }

    public void setRamPort(String ramPort) {
        this.ramPort = ramPort;
    }

    public String getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(String storagePort) {
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
