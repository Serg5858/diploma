package ru.netology.data;

import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return new CardData("4444 4444 4444 4441",getValidMonth(), getValidYear(), "Ivanov Ivan", "123");
    }

    public static CardData getDeclinedCard() {
        return new CardData("4444 4444 4444 4442", getValidMonth(), getValidYear(), "Ivanov Ivan", "123");
    }

    //Поле номер карты

    public static CardData getInvalidCardNumberIfEmpty() {
        return new CardData("", getValidMonth(), getValidYear(), "Ivanov Ivan", "123");
    }

    public static CardData getInvalidCardNumberIfLess16Symbol() {
        return new CardData("4444 4444 4444", getValidMonth(), getValidYear(), "Ivanov Ivan", "123");
    }

    public static CardData getInvalidCardNumberIfOutOfBase() {
        return new CardData("4444 4444 4444 4445", getValidMonth(), getValidYear(), "Ivanov Ivan", "123");
    }

//Поле месяц

    public static CardData getInvalidNumberOfMonthIfEmpty() {
        return new CardData("4444 4444 4444 4441", "", getValidYear(), "Ivanov Ivan", "123");
    }

    public static CardData getInvalidNumberOfMonthIfOneSym() {
        return new CardData("4444 4444 4444 4441", "1", getValidYear(), "Ivanov Ivan", "123");
    }

    public static CardData getInvalidNumberOfMonthIfMore12() {
        return new CardData("4444 4444 4444 4441", "33", getValidYear(), "Ivanov Ivan", "123");
    }

    public static CardData getInvalidNumberOfMonthIfZero() {
        return new CardData("4444 4444 4444 4441", "00",getValidYear(), "Ivanov Ivan", "123");
    }

    //Поле год

    public static CardData getInvalidYearIfEmpty() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), "", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfOneSym() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), "1", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfNotCurrentYear() {
        return new CardData("4444 4444 4444 4441",getValidMonth(), getPastYear(), "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfZero() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), "00", "Ivanov Ivan", "123");
    }

    public static CardData getInvalidYearIfInTheFuture() {
        return new CardData("4444 4444 4444 4441", getValidMonth(),getFutureYear(), "Ivanov Ivan", "123");
    }

//Поле владелец

    public static CardData getInvalidCardholderNameIfEmpty() {
        return new CardData("4444 4444 4444 4441",getValidMonth(), getValidYear(), "", "123");
    }

    public static CardData getInvalidCardholderNameIfOneWord() {
        return new CardData("4444 4444 4444 4441",getValidMonth(), getValidYear(), "Ivanov", "123");
    }

    public static CardData getInvalidCardholderNameIfThreeWords() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), getValidYear(), "Ivanov Ivan Ivanovitch", "123");
    }

    public static CardData getInvalidCardholderNameIfRusSym() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), getValidYear(), "Иванов Иван", "123");
    }

    public static CardData getInvalidCardholderNameIfNumeric() {
        return new CardData("4444 4444 4444 4441", getValidMonth(),getValidYear(), "1111 2222", "123");
    }

    public static CardData getInvalidCardholderNameIfSpecSymbol() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), getValidYear(), "#$%", "123");
    }

//Поле cvc-cvv

    public static CardData getInvalidCvvIfEmpty() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), getValidYear(), "Ivanov Ivan", "");
    }

    public static CardData getInvalidCvvIfOneSymbol() {
        return new CardData("4444 4444 4444 4441",getValidMonth(), getValidYear(), "Ivanov Ivan", "1");
    }

    public static CardData getInvalidCvvIfTwoSymbol() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), getValidYear(), "Ivanov Ivan", "12");
    }

    public static CardData getInvalidCvvIfThreeZero() {
        return new CardData("4444 4444 4444 4441", getValidMonth(), getValidYear(), "Ivanov Ivan", "000");
    }

    //генераторы месяца и года
    public static String getValidMonth() {
        String validMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        return validMonth;
    }

    public static String getValidYear() {
        String validYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return validYear;
    }

    public static String getPastYear() {
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return pastYear;
    }

    public static String getFutureYear() {
        String futureYear = LocalDate.now().plusYears(50).format(DateTimeFormatter.ofPattern("yy"));
        return futureYear;
    }
}





