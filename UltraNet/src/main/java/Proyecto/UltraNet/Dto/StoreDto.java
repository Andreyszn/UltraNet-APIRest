package Proyecto.UltraNet.Dto;

import java.time.LocalDate;

public class StoreDto {
    private String userEmail;
    private Integer HardwareId;
    private Double totalPrice;
    private LocalDate saleDate;
    private Integer quiantity;

    public StoreDto() {
    }

    public StoreDto(String userEmail, Integer hardwareId, Double totalPrice, LocalDate saleDate, Integer quiantity) {
        this.userEmail = userEmail;
        HardwareId = hardwareId;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.quiantity = quiantity;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getHardwareId() {
        return HardwareId;
    }

    public void setHardwareId(Integer hardwareId) {
        HardwareId = hardwareId;
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

    public Integer getQuiantity() {
        return quiantity;
    }

    public void setQuiantity(Integer quiantity) {
        this.quiantity = quiantity;
    }
}
