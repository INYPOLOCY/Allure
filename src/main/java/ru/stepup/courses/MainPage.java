package ru.stepup.courses;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private static final String PAGE_TITLE = "Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками";

    private SelenideElement logo = $("img[alt='«Авиакомпания «Победа», Группа «Аэрофлот»']");
    private SelenideElement infoMenu = $(By.xpath("//a[contains(text(), 'Информация')]"));
    private SelenideElement prepForFlight = $("a.dp-95zkn4-root-root[href='/information#flight']");
    private SelenideElement usefulInfo = $("a.dp-95zkn4-root-root[href='/information#useful']");
    private SelenideElement aboutCompany = $("a.dp-95zkn4-root-root[href='/information#company']");

    @Step("Открытие главной страницы")
    public MainPage open() {
        Selenide.open("https://pobeda.aero");
        return this;
    }

    @Step("Проверка отображения логотипа")
    public boolean isLogoDisplayed() {
        return logo.shouldBe(visible).exists();
    }

    @Step("Проверка заголовка страницы")
    public boolean isPageOpened() {
        return title().equals(PAGE_TITLE);
    }

    @Step("Наведение курсора на меню 'Информация'")
    public void hoverOverInfoMenu() {
        infoMenu.hover();
    }

    @Step("Проверка отображения всплывающего меню")
    public boolean isPopupDisplayed() {
        prepForFlight.shouldBe(visible);
        usefulInfo.shouldBe(visible);
        aboutCompany.shouldBe(visible);
        return true;
    }
}
