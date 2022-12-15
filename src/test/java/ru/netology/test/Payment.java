package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.SqlHelper;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SqlHelper.*;

public class Payment {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUpSutUrl() {
        mainPage = open("http://localhost:8080/", MainPage.class);
        mainPage.buyWithCard();
    }

    @AfterEach
    void cleanDataBases() {
        SqlHelper.cleanDataBase();
    }
    //Все тесты в этом файле на оплату дебетовой картой
    //Тесты с картами,подготовленными разработчиками.Позитивные тесты
    @Test
    public void shouldSuccessPayValidApprovedCard() {
        var cardData = getApprovedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldSuccessNotification();
        var expectedStatus = "APPROVED";
        var actualStatus = getCardStatusForPayment();
        assertEquals(expectedStatus,actualStatus);

    }

    @Test
    public void shouldFailurePayValidDeclinedCard() {
        var cardData = getDeclinedCard();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();

        var expectedStatus = "DECLINED";
        var actualStatus = getCardStatusForPayment();
        assertEquals(expectedStatus, actualStatus);


    }
    //тесты на поле номер карты
    @Test
    public void shouldFailurePaymentIfCardNumberIfOutOfBase() {
        var cardData = getInvalidCardNumberIfOutOfBase();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldFailureNotification();
    }
    @Test
    public void shouldFailurePaymentIfCardNumberIfLess16Sym() {
        var cardData = getInvalidCardNumberIfLess16Symbol();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }
    @Test
    public void shouldFailurePaymentIfEmptyCardNumber() {
        var cardData = getInvalidCardNumberIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }
    //тесты на поле месяц
    @Test
    public void shouldFailurePaymentIfEmptyNumberOfMonth() {
        var cardData = getInvalidNumberOfMonthIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfOneSymbol() {
        var cardData = getInvalidNumberOfMonthIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthIfMore12() {
        var cardData = getInvalidNumberOfMonthIfMore12();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfNumberOfMonthTwoZero() {
        var cardData = getInvalidNumberOfMonthIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }
    //тесты на поле год
    @Test
    public void shouldFailurePaymentIfEmptyYear() {
        var cardData = getInvalidYearIfEmpty();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldEmptyFieldNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearOneSymbol() {
        var cardData = getInvalidYearIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearBeforeCurrentYear() {
        var cardData = getInvalidYearIfNotCurrentYear();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldExpiredDatePassNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearZero() {
        var cardData = getInvalidYearIfZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }

    @Test
    public void shouldFailurePaymentIfYearInTheFuture() {
        var cardData = getInvalidYearIfInTheFuture();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldInvalidExpiredDateNotification();
    }
// тесты на поле владелец
@Test
public void shouldFailurePaymentIfEmptyCardholderName() {
    var cardData = getInvalidCardholderNameIfEmpty();
    paymentPage.fillCardData(cardData);
    paymentPage.shouldEmptyFieldNotification();
}

    @Test
    public void shouldFailurePaymentIfNameOneWord() {
        var cardData = getInvalidCardholderNameIfOneWord();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameThreeWords() {
        var cardData = getInvalidCardholderNameIfThreeWords();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameRusSymbol() {
        var cardData = getInvalidCardholderNameIfRusSym();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfNameNumeric() {
        var cardData = getInvalidCardholderNameIfNumeric();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }
    @Test
    public void shouldFailurePaymentIfNameSpecSymbol() {
        var cardData= getInvalidCardholderNameIfSpecSymbol();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }
    //тесты на поле CVC/CVV
    @Test
    public void shouldFailurePaymentIfEmptyCvv() {
        var cardData = getInvalidCvvIfEmpty();
        paymentPage.fillCardData(cardData);
        final ElementsCollection fieldSub = $$(".input__sub");
        final SelenideElement cvvFieldSub = fieldSub.get(2);
        cvvFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFailurePaymentIfCvvOneSymbol() {
        var cardData = getInvalidCvvIfOneSymbol();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCvvTwoSymbol() {
        var cardData = getInvalidCvvIfTwoSymbol();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCvvThreeZero() {
        var cardData = getInvalidCvvIfThreeZero();
        paymentPage.fillCardData(cardData);
        paymentPage.shouldIncorrectFormatNotification();
    }
}
