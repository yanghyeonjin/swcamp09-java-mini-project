package com.ohgiraffers.domain.product.service;

import com.ohgiraffers.domain.product.aggregate.Product;
import com.ohgiraffers.domain.product.repository.ProductRepository;
import com.ohgiraffers.domain.supplies.service.SuppliesService;
import com.ohgiraffers.exception.FailedPurchaseException;
import com.ohgiraffers.exception.NotFoundProductException;

import java.util.ArrayList;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    private final SuppliesService suppliesService = new SuppliesService();

    public ArrayList<Product> findAllProducts() {
        return productRepository.selectAllProducts();
    }

    public void purchase(int productNo, int quantity)
            throws NotFoundProductException, FailedPurchaseException {
        // 1. 품목이 있는지 체크
        Product selectedProduct = productRepository.selectProductByNo(productNo);
        if (selectedProduct == null) {
            throw new NotFoundProductException("일치하는 품목이 없어요!");
        }

        // 2. 법카 한도를 초과하는지 체크
        int totalPrice = selectedProduct.getPrice() * quantity;
        // 2-예외. 법카 한도를 초과하면 구매 불가 fail

        // 3. 법카 사용한도를 추가한다.

        // 4. 비품 목록에 구매한 비품을 추가한다.
        int result = suppliesService.AddSupplies(selectedProduct, quantity);
        if (result == 1) {
            System.out.println("비품 구매 성공!!");
        } else {
            throw new FailedPurchaseException("구매에 성공하지 못했어요. (비품 추가 실패)");
        }
    }
}
