package com.ohgiraffers.domain.product.aggregate;

public class Product {
    private int productNo;          // 상품번호
    private String productName;     // 상품명
    private int price;              // 상품가격

    public Product() {
    }

    public Product(int productNo, String productName, int price) {
        this.productNo = productNo;
        this.productName = productName;
        this.price = price;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNo=" + productNo +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
