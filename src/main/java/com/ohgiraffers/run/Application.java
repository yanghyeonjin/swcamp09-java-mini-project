package com.ohgiraffers.run;

import com.ohgiraffers.domain.supplies.aggregate.Supplies;
import com.ohgiraffers.domain.supplies.service.SuppliesService;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    private static final SuppliesService suppliesService = new SuppliesService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== 비품 관리 프로그램이 시작되었습니다. ===");
            System.out.println("1: 비품의 재고 확인");
            System.out.println("2: 비품 구매");
            System.out.println("3: 비품 사용");
            System.out.println("4: 법카 잔여 한도 확인");
            System.out.println("9: 프로그램 종료");
            System.out.print("원하시는 메뉴를 입력하세요: ");

            int menuNo = scanner.nextInt();

            switch (menuNo) {
                case 1:
                    ArrayList<Supplies> foundSupplies = suppliesService.findAllSupplies();
                    printSupplies(foundSupplies);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }

    // 현재 재고 리스트를 출력하는 메소드
    private static void printSupplies(ArrayList<Supplies> foundSupplies) {
        if (foundSupplies.isEmpty()) {  // 보유하고 있는 비품이 없을 때
            System.out.println("**************************");
            System.out.println("**** 텅 ~ 아무것도 없어요 ****");
            System.out.println("**************************");
            return;
        }

        for (Supplies supplies : foundSupplies) {
            int no = supplies.getSuppliesNo();
            String name = supplies.getSuppliesName();
            int quantity = supplies.getQuantity();

            System.out.println("---- 현재 비품 목록 ----");
            System.out.println(no + ": " + name + " (재고수량: " + quantity + "개)");
        }
    }
}
