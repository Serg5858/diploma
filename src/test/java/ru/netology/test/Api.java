package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.data.ApiHelper.CreditRequestPageForm;
import static ru.netology.data.ApiHelper.PaymentPageForm;
import static ru.netology.data.DataHelper.getApprovedCard;
import static ru.netology.data.DataHelper.getDeclinedCard;

public class Api {
    @Test
    void shouldGetResponseForValidApprovedCardPayment() {
        var validApprovedCard = getApprovedCard();
        var response = PaymentPageForm(validApprovedCard);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGetResponseForValidDeclinedCardPayment() {
        var validDeclinedCard = getDeclinedCard();
        var response = PaymentPageForm(validDeclinedCard);
        assertTrue(response.contains("DECLINED"));
    }

    @Test
    void shouldGetResponseForValidApprovedCardCreditRequest() {
        var validApprovedCard = getApprovedCard();
        var response = CreditRequestPageForm(validApprovedCard);
        assertTrue(response.contains("APPROVED"));
    }

    @Test
    void shouldGetResponseForValidDeclinedCardCreditRequest() {
        var validDeclinedCard = getDeclinedCard();
        var response = CreditRequestPageForm(validDeclinedCard);
        assertTrue(response.contains("DECLINED"));
    }
}
