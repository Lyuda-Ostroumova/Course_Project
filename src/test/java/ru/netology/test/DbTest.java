package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.mifmif.common.regex.Main;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.DbUtils;
import ru.netology.page.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataGenerator.*;

public class DbTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void OpenSource() {
        open("http://localhost:8080");
        DbUtils.cleanTable();
    }

    @Test
    void shouldApprovePaymentWithApprovedCard() {
        val mainPage = new MainPage();
        val cardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.paymentSuccessful();
        assertEquals("APPROVED", DbUtils.getStatusOfPayment());
    }

    @Test
    void shouldDeclinePaymentWithDeclinedCard() {
        val mainPage = new MainPage();
        val cardInfo = new DataGenerator.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.paymentDeclined();
        assertEquals("DECLINED", DbUtils.getStatusOfPayment());
    }

}
