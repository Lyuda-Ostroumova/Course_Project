package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class PurchasePage {

    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$("[class='input__control']");
    private SelenideElement month = $(byText("Месяц")).parent().$("[class='input__control']");
    private SelenideElement year = $(byText("Год")).parent().$("[class='input__control']");
    private SelenideElement cardOwner = $(byText("Владелец")).parent().$("[class='input__control']");
    private SelenideElement cvc = $(byText("CVC/CVV")).parent().$("[class='input__control']");
    private SelenideElement button = $(byText("Продолжить"));
    private SelenideElement cardNumberError = $(byText("Номер карты")).parent().$("[class='input__sub']");
    private SelenideElement monthFormatError = $(byText("Месяц")).parent().$("[class='input__sub']");
    private SelenideElement monthError = $(byText("Неверно указан срок действия карты")).parent().$("[class='input__sub']");
    private SelenideElement yearFormatError = $(byText("Год")).parent().$("[class='input__sub']");
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты")).parent().$("[class='input__sub']");
    private SelenideElement cardOwnerError = $(byText("Владелец")).parent().$("[class='input__sub']");
    private SelenideElement cvcFormatError = $(byText("CVC/CVV")).parent().$("[class='input__sub']");
    private SelenideElement paymentError = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class='notification__icon']");
    private SelenideElement paymentSuccessful = $(byText("Операция одобрена Банком.")).parent().$("[class='notification__icon']");

    public void fillInForm(CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        cardOwner.setValue(cardInfo.getOwner());
        cvc.setValue(cardInfo.getCvc());
        button.click();
    }

    public void emptyForm() {
        button.click();
        cardNumberError.shouldBe(Condition.visible);
        monthFormatError.shouldBe(Condition.visible);
        yearFormatError.shouldBe(Condition.visible);
        cardOwnerError.shouldBe(Condition.visible);
        cvcFormatError.shouldBe(Condition.visible);

    }

    public void showCardNumberError() {
        cardNumberError.shouldBe(Condition.visible);
    }

    public void showMonthFormatError() {
        monthFormatError.shouldBe(Condition.visible);
    }

    public void showMonthError() {
        monthError.shouldBe(Condition.visible);
    }

    public void showYearFormatError() {
        yearFormatError.shouldBe(Condition.visible);
    }

    public void showCardExpiredError() {
        cardExpiredError.shouldBe(Condition.visible);
    }

    public void showCardOwnerError() {
        cardOwnerError.shouldBe(Condition.visible);
    }

    public void showCvcFormatError() {
        cvcFormatError.shouldBe(Condition.visible);
    }

    public void paymentDeclined() {
        paymentError.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public void paymentSuccessful() {
        paymentSuccessful.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
}






