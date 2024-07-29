package com.ohgiraffers.domain.card.management;

import com.ohgiraffers.domain.card.aggregate.Card;
import com.ohgiraffers.domain.card.repository.CardRepository;

public class CardManagement {

    private final CardRepository cardRepo = new CardRepository();

    public CardManagement() {
    }

    public void checkCreditLimit() {
        Card selectedCard = cardRepo.selectMyCard();

        if (selectedCard != null) {
            if (selectedCard.getCreditCardLimit() >= selectedCard.getAmountOfCardUsed()) {
                System.out.println(selectedCard.getCreditCardName() + " 카드의 잔여 한도는 " +
                        (selectedCard.getCreditCardLimit() - selectedCard.getAmountOfCardUsed()) + "원 입니다.");
            }
        }

    }
}
