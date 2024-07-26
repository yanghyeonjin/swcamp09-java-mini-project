package com.ohgiraffers.domain.product.repository;

import com.ohgiraffers.domain.product.aggregate.Product;

import java.util.ArrayList;

public class ProductRepository {

    private ArrayList<Product> products = new ArrayList<>();

    public ProductRepository() {
        // 초기 상품목록 셋팅
        products.add(new Product(1, "스카치테이프", 1_000));
        products.add(new Product(2, "박스테이프", 2_000));
        products.add(new Product(3, "롤휴지(24개입)", 10_000));
        products.add(new Product(4, "각티슈", 1_000));
        products.add(new Product(5, "물티슈(100매)", 2_000));
        products.add(new Product(6, "비누", 500));
        products.add(new Product(7, "핸드워시", 3_500));
        products.add(new Product(8, "무선청소기", 300_000));
        products.add(new Product(9, "A4용지(2500매)", 26_400));
    }
}
