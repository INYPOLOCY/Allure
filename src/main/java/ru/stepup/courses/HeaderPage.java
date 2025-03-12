package ru.stepup.courses;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class HeaderPage {
    private final SelenideElement headerLogo = $("img[alt='«Авиакомпания «Победа», Группа «Аэрофлот»']");
    private final SelenideElement bookingManagementButton = $("button:contains('Управление бронированием')");

    @Step("Проверка отображения логотипа в хедере")
    public boolean isHeaderLogoDisplayed() {
        return headerLogo.shouldBe(visible).exists();
    }

    @Step("Открытие страницы управления бронированием")
    public void openBookingManagement() {
        bookingManagementButton.click();
    }
}
