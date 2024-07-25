package com.ohgiraffers.domain.supplies.aggregate;

import java.util.Date;

public class Supplies {
    private String suppliesName;            // 비품명
    private int price;                      // 비품가격
    private int quantity;                   // 재고수량
    private java.util.Date purchaseDate;    // 최근구매일

    public Supplies() {
    }

    public Supplies(String suppliesName, int price, int quantity, Date purchaseDate) {
        this.suppliesName = suppliesName;
        this.price = price;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public String getSuppliesName() {
        return suppliesName;
    }

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
                "suppliesName='" + suppliesName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
