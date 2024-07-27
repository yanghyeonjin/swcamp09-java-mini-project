package com.ohgiraffers.domain.supplies.service;

import com.ohgiraffers.domain.product.aggregate.Product;
import com.ohgiraffers.domain.supplies.aggregate.Supplies;
import com.ohgiraffers.domain.supplies.repository.SuppliesRepository;

import java.util.ArrayList;

public class SuppliesService {

    private static final SuppliesRepository suppliesRepository = new SuppliesRepository();

    public ArrayList<Supplies> findAllSupplies() {
        return suppliesRepository.selectAllSupplies();
    }

    public int AddSupplies(Product selectedProduct, int quantity) {
        Supplies selectedSupplies = suppliesRepository.selectSuppliesByNo(selectedProduct.getProductNo());
        if (selectedSupplies == null) {     // 기존에 없는 목록이라면 비품 자체를 새로 추가한다.
            return suppliesRepository.insertSupplies(selectedProduct, quantity);
        }

        // 이미 있는 경우 재고수량만 증가시킨다.
        return suppliesRepository.increaseSuppliesQuantity(selectedSupplies.getSuppliesNo(), quantity);
    }
}
