package Proyecto.UltraNet.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<Integer> quantity;
    private Double totalPrice;
    private LocalDate saleDate;
    @ManyToMany
    private List<Hardware> hardware;
    @ManyToOne
    private User user;

    public Store() {
    }

    public Store(User user, List<Hardware> hardware, LocalDate saleDate, Double totalPrice, List<Integer> quantity, Integer id) {
        this.user = user;
        this.hardware = hardware;
        this.saleDate = saleDate;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Hardware> getHardware() {
        return hardware;
    }

    public void setHardware(List<Hardware> hardware) {
        this.hardware = hardware;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}
