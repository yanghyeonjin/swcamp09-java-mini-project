package com.ohgiraffers.domain.card.aggregate;

public class Card {
    private String creditCardName;      // 카드명+번호
    private int creditCardLimit;        // 카드 한도
    private int amountOfCardUsed;       // 사용한 카드 금액

    public Card() {
    }

    public Card(String creditCardName, int creditCardLimit, int amountOfCardUsed) {
        this.creditCardName = creditCardName;
        this.creditCardLimit = creditCardLimit;
        this.amountOfCardUsed = amountOfCardUsed;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public int getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(int creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public int getAmountOfCardUsed() {
        return amountOfCardUsed;
    }

    public void setAmountOfCardUsed(int amountOfCardUsed) {
        this.amountOfCardUsed = amountOfCardUsed;
    }

    @Override
    public String toString() {
        return "card{" +
                "creditCardName='" + creditCardName + '\'' +
                ", creditCardLimit=" + creditCardLimit +
                ", amountOfCardUsed=" + amountOfCardUsed +
                '}';
    }
}
