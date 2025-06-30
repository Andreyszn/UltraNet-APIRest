package Proyecto.UltraNet.Dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StoreDto {
    private List<Integer> hardwareId = new ArrayList<>();
    private LocalDate saleDate;
    private List<Integer> quantity = new ArrayList<>();

    public StoreDto() {
    }

    public StoreDto(List<Integer> hardwareId, LocalDate saleDate, List<Integer> quantity) {
        this.hardwareId = hardwareId;
        this.saleDate = saleDate;
        this.quantity = quantity;
    }

    public List<Integer> getHardwareId() {
        return hardwareId;
    }

    public void setHardwareId(List<Integer> hardwareId) {
        this.hardwareId = hardwareId;
    }


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
