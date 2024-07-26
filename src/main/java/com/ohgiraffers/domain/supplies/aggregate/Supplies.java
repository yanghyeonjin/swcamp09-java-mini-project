package com.ohgiraffers.domain.supplies.aggregate;

import java.util.Date;

public class Supplies {
    private int suppliesNo;                 // 비품번호
    private String suppliesName;            // 비품명
    private int quantity;                   // 재고수량
    private java.util.Date purchaseDate;    // 최근구매일

    public Supplies() {
    }

    public Supplies(int suppliesNo, String suppliesName, int quantity, Date purchaseDate) {
        this.suppliesNo = suppliesNo;
        this.suppliesName = suppliesName;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public int getSuppliesNo() {
        return suppliesNo;
    }

    public void setSuppliesNo(int suppliesNo) {
        this.suppliesNo = suppliesNo;
    }

    public String getSuppliesName() {
        return suppliesName;
    }

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Supplies{" +
                "suppliesNo=" + suppliesNo +
                ", suppliesName='" + suppliesName + '\'' +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
