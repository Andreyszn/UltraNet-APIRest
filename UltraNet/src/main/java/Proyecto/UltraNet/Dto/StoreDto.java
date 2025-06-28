package Proyecto.UltraNet.Dto;

import java.time.LocalDate;

public class StoreDto {
    private String userEmail;
    private Integer hardwareId;
    private LocalDate saleDate;
    private Integer quantity;

    public StoreDto() {
    }

    public StoreDto(String userEmail, Integer hardwareId, Double totalPrice, LocalDate saleDate, Integer quantity) {
        this.userEmail = userEmail;
        this.hardwareId = hardwareId;
        this.saleDate = saleDate;
        this.quantity = quantity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        this.hardwareId = hardwareId;
    }

//    public Double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Double totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
