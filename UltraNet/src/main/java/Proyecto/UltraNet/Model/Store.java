package Proyecto.UltraNet.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private Double totalPrice;
    private LocalDate saleDate;
    @ManyToOne
    private Hardware hardware;
    @ManyToOne
    private User user;

    public Store() {
    }

    public Store(User user, Hardware hardware, LocalDate saleDate, Double totalPrice, Integer quantity, Integer id) {
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

    public Hardware getHardware() {
        return hardware;
    }

    public void setHardware(Hardware hardware) {
        this.hardware = hardware;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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
