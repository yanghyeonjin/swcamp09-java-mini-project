package com.ohgiraffers.run;

import com.ohgiraffers.domain.card.management.CardManagement;

import java.util.Scanner;

public class Application {

    private static final CardManagement cardManagement = new CardManagement();

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
                    break;
                case 2:
                    break;
                case 3:
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
}
