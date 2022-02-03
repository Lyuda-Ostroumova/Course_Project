package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;


import ru.netology.page.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.*;

public class TourPurchaseTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }


    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:8080");
    }

    // passed
    @Test
    public void shouldShowPurchasePage() {
        val mainPage = new MainPage();
        mainPage.purchaseByCard();
    }

    // passed
    @Test
    public void shouldShowCreditPage() {
        val mainPage = new MainPage();
        mainPage.purchaseOnCredit();
    }

    // passed
    @Test
    public void shouldPurchaseByCardSuccessfully() {
        val mainPage = new MainPage();
        val cardInfo = new DataGenerator.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.paymentSuccessful();
    }

    // failed
    @Test
    public void shouldDeclinePurchaseByCard() {
        val mainPage = new MainPage();
        val cardInfo = new DataGenerator.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.paymentDeclined();
    }

    // passed
    @Test
    public void shouldDeclinePurchaseByRandomCard() {
        val mainPage = new MainPage();
        val cardInfo = new DataGenerator.CardInfo(getRandomCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.paymentDeclined();
    }

    // passed
    @Test
    public void shouldShowErrorIfCardNumberIsShort() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getShortCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardNumberError();
    }

    // passed
    @Test
    public void shouldShowErrorIfCardNumberContainsLetters() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getCardNumberWithLetters(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardNumberError();
    }

    // passed
    @Test
    public void shouldShowErrorIfCardNumberContainsSymbols() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getCardNumberWithSymbols(), getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardNumberError();
    }

    // passed
    @Test
    public void shouldShowErrorIfCardNumberIsEmpty() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(null, getValidMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardNumberError();
    }

    // passed
    @Test
    public void shouldShowErrorIfNonExistentMonth() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getNonExistentMonth(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthError();
    }

    // passed
    @Test
    public void shouldShowErrorIfMonthWithZeros() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthWithZeroes(), getValidYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthFormatError();
    }

    // failed
    @Test
    public void shouldShowErrorIfMonthWithZerosAndFutureYear() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthWithZeroes(), getFutureYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthError();
    }

    // passed
    @Test
    public void shouldShowErrorIfMonthOfOneNumber() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthOfOneNumber(), getFutureYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfMonthWithLetters() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthWithLetters(), getFutureYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfMonthWithSymbols() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthWithSymbols(), getFutureYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfMonthIsEmpty() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), null, getFutureYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showMonthFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfPastYear() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getPastYear(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardExpiredError();
    }

    // passed
    @Test
    public void shouldShowErrorIfYearOfOneNumber() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getYearOfOneNumber(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showYearFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfYearWithLetters() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthWithLetters(), getYearWithLetters(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showYearFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfYearWithSymbols() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getMonthWithSymbols(), getYearWithSymbols(), getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showYearFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfYearIsEmpty() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), null, getCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showYearFormatError();
    }

    // failed
    @Test
    public void shouldShowErrorIfNameInRus() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerNameInRus(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // failed
    @Test
    public void shouldShowErrorIfNoLastName() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerFirstName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // failed
    @Test
    public void shouldShowErrorIfNoFirstName() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerLastName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // failed
    @Test
    public void shouldShowErrorIfCardOwnerNameWithOneLetterOnly() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerNameWithOneLetter(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // failed
    @Test
    public void shouldShowErrorIfCardOwnerNameTooLong() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getLongCardOwnerName(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // passed
    @Test
    public void shouldPayIfCardOwnerNameWithHyphen() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerNameWithHyphen(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.paymentSuccessful();
    }

    // failed
    @Test
    public void shouldShowErrorIfCardOwnerNameWithNumbers() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerNameWithNumbers(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // failed
    @Test
    public void shouldShowErrorIfCardOwnerNameWithSymbols() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerNameWithSymbols(), getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // passed
    @Test
    public void shouldShowErrorIfCardOwnerNameIsEmpty() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), null, getValidCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCardOwnerError();
    }

    // failed
    @Test
    public void shouldShowErrorIfCvcWithZero() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getCvcWithZero());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCvcFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfShortCvc() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getShortCvc());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCvcFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfCvcWithLetters() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getCvcWithLetters());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCvcFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorIfCvcWithSymbols() {
        val mainPage = new MainPage();
        val cardInfo = new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getCardOwnerName(), getCvcWithSymbols());
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.fillInForm(cardInfo);
        purchasePage.showCvcFormatError();
    }

    // passed
    @Test
    public void shouldShowErrorsIfFieldsEmpty() {
        val mainPage = new MainPage();
        val purchasePage = mainPage.purchaseByCard();
        purchasePage.emptyForm();
    }

}


