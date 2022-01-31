package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement purchaseButton = $("[class='button button_size_m button_theme_alfa-on-white']");
    private SelenideElement creditButton = $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']");
    private SelenideElement purchaseByCard = $(byText("Оплата по карте"));
    private SelenideElement purchaseOnCredit = $(byText("Кредит по данным карты"));


    public PurchasePage purchaseByCard() {
        purchaseButton.click();
        purchaseByCard.shouldBe(Condition.visible);
        return new PurchasePage();
    }

    public CreditPage purchaseOnCredit() {
        creditButton.click();
        purchaseOnCredit.shouldBe(Condition.visible);
        return new CreditPage();
    }

}
