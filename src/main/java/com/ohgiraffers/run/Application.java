package com.ohgiraffers.run;

import com.ohgiraffers.domain.card.management.CardManagement;
import com.ohgiraffers.domain.product.aggregate.Product;
import com.ohgiraffers.domain.product.service.ProductService;
import com.ohgiraffers.domain.supplies.aggregate.Supplies;
import com.ohgiraffers.domain.supplies.service.SuppliesService;
import com.ohgiraffers.exception.FailedPurchaseException;
import com.ohgiraffers.exception.InsufficientQuantityException;
import com.ohgiraffers.exception.NotFoundProductException;
import com.ohgiraffers.exception.NotFoundSuppliesException;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private static final SuppliesService suppliesService = new SuppliesService();
    private static final ProductService productService = new ProductService();
    private static final CardManagement cardManagement = new CardManagement();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== 비품 관리 프로그램이 시작되었습니다. ===");
            System.out.println("1: 비품 목록 확인");
            System.out.println("2: 비품 구매");
            System.out.println("3: 비품 사용");
            System.out.println("4: 법카 잔여 한도 확인");
            System.out.println("9: 프로그램 종료");
            System.out.print("원하시는 메뉴 번호를 입력하세요: ");

            int menuNo = scanner.nextInt();

            switch (menuNo) {
                case 1:
                    searchAllSupplies();
                    break;
                case 2:
                    ArrayList<Product> foundProducts = productService.findAllProducts();
                    printProducts(foundProducts);

                    try {
                        productService.purchase(chooseProductNo("구매할 "), chooseProductQuantity("구매할 "));
                    } catch (NotFoundProductException | FailedPurchaseException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    searchAllSupplies();
                    try {
                        suppliesService.useSupplies(chooseProductNo("사용할 "), chooseProductQuantity("사용할 "));
                    } catch (NotFoundSuppliesException | InsufficientQuantityException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4: cardManagement.checkCreditLimit();
                    System.out.println();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }

    private static void searchAllSupplies() {
        ArrayList<Supplies> foundSupplies = suppliesService.findAllSupplies();
        printSupplies(foundSupplies);
    }

    private static int chooseProductQuantity(String prefixStr) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prefixStr + "수량을 입력하세요: ");
        return scanner.nextInt();
    }

    private static int chooseProductNo(String prefixStr) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prefixStr + "품목 번호를 입력하세요: ");
        return scanner.nextInt();
    }

    // 구매 가능한 품목 리스트를 출력
    private static void printProducts(ArrayList<Product> foundProducts) {
        System.out.println("---- 구매 가능한 품목 리스트 ----");

        for (Product product : foundProducts) {
            int no = product.getProductNo();
            String name = product.getProductName();
            int price = product.getPrice();
            String priceComma = String.format("%,d", price);

            System.out.println(no + ": " + name + " (" + priceComma + "원)");
        }

        System.out.println("---- ------------ ------");
    }

    // 현재 재고 리스트를 출력하는 메소드
    private static void printSupplies(ArrayList<Supplies> foundSupplies) {
        if (foundSupplies.isEmpty()) {  // 보유하고 있는 비품이 없을 때
            System.out.println("**************************");
            System.out.println("**** 텅 ~ 아무것도 없어요 ****");
            System.out.println("**************************");
            return;
        }

        System.out.println("---- 현재 비품 목록 ----");

        for (Supplies supplies : foundSupplies) {
            int no = supplies.getSuppliesNo();
            String name = supplies.getSuppliesName();
            int quantity = supplies.getQuantity();


            System.out.println(no + ": " + name + " (재고수량: " + quantity + "개)");
        }

        System.out.println("---- ------------ ------");
    }
}
