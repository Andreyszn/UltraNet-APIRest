package Proyecto.UltraNet.Dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StoreDto {
    private String userEmail;
    private List<Integer> hardwareId = new ArrayList<>();
    private LocalDate saleDate;
    private List<Integer> quantity = new ArrayList<>();

    public StoreDto() {
    }

    public StoreDto(String userEmail, List<Integer> hardwareId, LocalDate saleDate, List<Integer> quantity) {
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

    public List<Integer> getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(List<Integer> hardwareId) {
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

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
