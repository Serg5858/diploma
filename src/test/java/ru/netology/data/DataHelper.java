package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
public class DataHelper {

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvv;
    }

    public static CardData getApprovedCard() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov Ivan", "123");
    }

    public static CardData getDeclinedCard() {
        return new CardData("4444 4444 4444 4442", "09", "23", "Ivanov Ivan", "123");
    }

    //Поле номер карты

    public static CardData getInvalidCardNumberIfEmpty() {
        return new CardData("", "09", "23", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidCardNumberIfLess16Symbol() {
        return new CardData("4444 4444 4444", "09", "23", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidCardNumberIfOutOfBase() {
        return new CardData("4444 4444 4444 4445", "09", "23", "Ivanov Ivan", "123");
    }

//Поле месяц

    public static CardData getInvalidNumberOfMonthIfEmpty() {
        return new CardData("4444 4444 4444 4441", "", "23", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidNumberOfMonthIfOneSym() {
        return new CardData("4444 4444 4444 4441", "1", "23", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidNumberOfMonthIfMore12() {
        return new CardData("4444 4444 4444 4441", "33", "23", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidNumberOfMonthIfZero() {
        return new CardData("4444 4444 4444 4441", "00", "23", "Ivanov Ivan", "123");
    }

    //Поле год

    public static CardData getInvalidYearIfEmpty() {
        return new CardData("4444 4444 4444 4441", "09", "", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfOneSym() {
        return new CardData("4444 4444 4444 4441", "09", "1", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfNotCurrentYear() {
        return new CardData("4444 4444 4444 4441", "09", "19", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfZero() {
        return new CardData("4444 4444 4444 4441", "09", "00", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfInTheFuture() {
        return new CardData("4444 4444 4444 4441", "09", "46", "Ivanov Ivan", "123");
    }

//Поле владелец

    public static CardData getInvalidCardholderNameIfEmpty() {
        return new CardData("4444 4444 4444 4441", "09", "23", "", "123");
    }

    public static CardData getInvalidCardholderNameIfOneWord() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov", "123");
    }

    public static CardData getInvalidCardholderNameIfThreeWords() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov Ivan Ivanovitch", "123");
    }

    public static CardData getInvalidCardholderNameIfRusSym() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Иванов Иван", "123");
    }

    public static CardData getInvalidCardholderNameIfNumeric() {
        return new CardData("4444 4444 4444 4441", "09", "23", "1111 2222", "123");
    }

    public static CardData getInvalidCardholderNameIfSpecSymbol() {
        return new CardData("4444 4444 4444 4441", "09", "23", "#$%", "123");
    }

//Поле cvc-cvv

    public static CardData getInvalidCvvIfEmpty() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov Ivan", "");
    }

    public static CardData getInvalidCvvIfOneSym() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov Ivan", "1");
    }

    public static CardData getInvalidCvvIfTwoSymbol() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov Ivan", "12");
    }

    public static CardData getInvalidCvvIfThreeZero() {
        return new CardData("4444 4444 4444 4441", "09", "23", "Ivanov Ivan", "000");
    }

    public static CardData getInvalidCardDataIfEmptyAllFields() {
        return new CardData("", "", "", "", "");
    }
}



