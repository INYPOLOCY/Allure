package ru.stepup.courses;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BookingManagementPage {
    private final SelenideElement orderNumberInput = $x("//input[@placeholder='Номер бронирования или билета']");
    private final SelenideElement lastNameInput = $x("//input[@placeholder='Фамилия клиента']");
    private final SelenideElement searchButton = $x("//button[span[contains(text(), 'Поиск')]]");
    private final SelenideElement errorMessage = $x("//div[contains(@class, 'message_error')]");
    private final SelenideElement bookingManagementLink = $x("//button[contains(., 'Управление бронированием')]");

    @Step("Проверка отображения поля 'Номер заказа'")
    public boolean isOrderNumberFieldDisplayed() {
        return orderNumberInput.shouldBe(visible).exists();
    }

    @Step("Проверка отображения поля 'Фамилия клиента'")
    public boolean isLastNameFieldDisplayed() {
        return lastNameInput.shouldBe(visible).exists();
    }

    @Step("Проверка отображения кнопки 'Поиск'")
    public boolean isSearchButtonDisplayed() {
        return searchButton.shouldBe(visible).exists();
    }

    @Step("Ввод номера бронирования: {orderNumber}")
    public void enterOrderNumber(String orderNumber) {
        orderNumberInput.setValue(orderNumber);
    }

    @Step("Ввод фамилии клиента: {lastName}")
    public void enterLastName(String lastName) {
        lastNameInput.setValue(lastName);
    }

    @Step("Нажатие кнопки поиска")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Step("Переход на страницу управления бронированием")
    public void goToBookingManagement() {
        bookingManagementLink.click();
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        switchTo().window(1);
        return errorMessage.shouldBe(visible).exists();
    }
}
