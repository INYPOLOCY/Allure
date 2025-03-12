package ru.stepup.courses;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchBlockPage {
    private final SelenideElement searchBlock = $("#__next form");
    private final SelenideElement fromInput = $x("//input[@placeholder='Откуда']");
    private final SelenideElement toInput = $x("//input[@placeholder='Куда']");
    private final SelenideElement departureDateInput = $x("//input[@placeholder='Туда']");
    private final SelenideElement returnDateInput = $x("//input[@placeholder='Обратно']");
    private final SelenideElement searchButton = $x("//div[@id='__next']//form//button[.//span[contains(text(), 'Поиск')]]");

    @Step("Проверка отображения блока поиска")
    public boolean isSearchBlockDisplayed() {
        return searchBlock.shouldBe(visible).exists();
    }

    @Step("Скролл к блоку поиска")
    public void scrollToSearchBlock() {
        searchBlock.scrollIntoView(true);
    }

    @Step("Проверка отображения полей ввода")
    public boolean areFieldsDisplayed() {
        return fromInput.shouldBe(visible).exists()
                && toInput.shouldBe(visible).exists()
                && departureDateInput.shouldBe(visible).exists()
                && returnDateInput.shouldBe(visible).exists();
    }

    @Step("Ввод города отправления: ")
    public void enterFrom(String from) {
        fromInput.click();
        fromInput.setValue(from).pressEnter();
    }

    @Step("Ввод города прибытия: ")
    public void enterTo(String to) {
        toInput.setValue(to);
        $x("//div[contains(@class, 'dp-20s1up-root-suggestionName') and text()='" + to + "']").click();
    }

    @Step("Нажатие кнопки поиска")
    public void clickSearch() {
        searchButton.click();
    }
}
