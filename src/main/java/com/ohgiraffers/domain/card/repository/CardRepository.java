package com.ohgiraffers.domain.card.repository;

import com.ohgiraffers.domain.card.aggregate.Card;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class CardRepository {

    private HashSet<Card> cardSet = new HashSet<>();    // 카드를 HashSet에 저장

    public int remainingLimit;      // 잔여 한도(카드 한도 - 사용한 카드 금액)를 담을 변수

    public CardRepository() {

        File file = new File("src/main/java/com/ohgiraffers/domain/card/db/cardDB.dat");
        if (!file.exists()) {
            HashSet<Card> defaultCard = new HashSet<>();
            defaultCard.add(new Card("국민9120", 1000000, 0));

            saveCard(file, defaultCard);
        }

        loadCard(file);
    }

    private void loadCard(File file) {                          // 파일로부터 카드 정보 불러오기
        ObjectInputStream cardInputStream = null;
        try {
            cardInputStream = new ObjectInputStream(new FileInputStream(file));

            while (true) {
                cardSet.add((Card) cardInputStream.readObject());
            }

        } catch (EOFException e){
            System.out.println("카드 정보 로딩 완료");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(cardInputStream != null) cardInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void saveCard(File file, HashSet<Card> cardSet) {           // 카드 객체를 파일로 출력
        ObjectOutputStream cardOutputStream = null;
        try {
            cardOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            for (Card card : cardSet) {
                cardOutputStream.writeObject(card);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(cardOutputStream != null) cardOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Card selectMyCard() {
        Iterator<Card> iterator = cardSet.iterator();
        while (iterator.hasNext()) {
            return iterator.next();
        }

        return null;
    }

    // 비품 구매 발생에 따라 사용한 카드 금액 update 로직 필요


}
