package com.ohgiraffers.domain.supplies.service;

import com.ohgiraffers.domain.product.aggregate.Product;
import com.ohgiraffers.domain.supplies.aggregate.Supplies;
import com.ohgiraffers.domain.supplies.repository.SuppliesRepository;
import com.ohgiraffers.exception.InsufficientQuantityException;
import com.ohgiraffers.exception.NotFoundSuppliesException;

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

    // 비품을 사용한다.
    public void useSupplies(int useSuppliesNo, int useSuppliesQuantity)
            throws NotFoundSuppliesException, InsufficientQuantityException {
        // 사용할 비품의 수량을 체크
        Supplies useSupplies = suppliesRepository.selectSuppliesByNo(useSuppliesNo);
        if (useSupplies == null) {
            throw new NotFoundSuppliesException("비품을 찾지 못했어요.");
        }

        if (useSupplies.getQuantity() < useSuppliesQuantity) {
            throw new InsufficientQuantityException("비품 수량이 충분하지 않아요.");
        }

        int result = suppliesRepository.updateQuantity(useSuppliesNo, useSuppliesQuantity);
        if (result == 1) {
            System.out.println("비품이 사용되었습니다.");
        } else {
            System.out.println("무언가 오류가 발생했습니다.");
        }
    }
}
