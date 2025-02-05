package com.ohgiraffers.domain.card.management;

import com.ohgiraffers.domain.card.aggregate.Card;
import com.ohgiraffers.domain.card.repository.CardRepository;

public class CardManagement {

    private static final CardRepository cardRepo = new CardRepository();

    public CardManagement() {
    }

    public void checkCreditLimit() {
        Card selectedCard = cardRepo.selectMyCard();

        if (selectedCard != null) {
            System.out.println(selectedCard.getCreditCardName() + " 카드의 잔여 한도는 " +
                    (selectedCard.getCreditCardLimit() - selectedCard.getAmountOfCardUsed()) + "원 입니다.");
        }
    }

    public int getCreditCardLimit() {
        // 나의 법카의 잔여 한도를 가져오는 부분
        return cardRepo.selectMyCard().getCreditCardLimit() - cardRepo.selectMyCard().getAmountOfCardUsed();
    }

    public void subtractCreditLimit(int billing) {
        cardRepo.updateAccum(billing);
    }
}
