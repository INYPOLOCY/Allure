package ru.stepup.courses;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тестирование сайта Победа")
@DisplayName("Тесты на основные функции сайта авиакомпании Победа")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainPageTest {
    private static MainPage mainPage;
    private static SearchBlockPage searchBlockPage;
    private static BookingManagementPage bookingManagementPage;
    private static HeaderPage headerPage;

    @BeforeAll
    static void setUp() {
        Configuration.browser = "firefox";
        Configuration.headless = false;
        Configuration.timeout = 30000;
        open("https://pobeda.aero");
        mainPage = new MainPage();
        searchBlockPage = new SearchBlockPage();
        bookingManagementPage = new BookingManagementPage();
        headerPage = new HeaderPage();
    }

    @Test
    @Order(1)
    @Feature("Меню информации")
    @Description("Проверка работы всплывающего меню информации")
    void testPopupMenu() {
        Assertions.assertAll(
                () -> assertTrue(mainPage.isPageOpened(), "Страница не открылась корректно"),
                () -> assertTrue(mainPage.isLogoDisplayed(), "Логотип не найден")
        );

        mainPage.hoverOverInfoMenu();
        assertTrue(mainPage.isPopupDisplayed(), "Всплывающее окно не появилось");
    }

    @Test
    @Order(2)
    @Feature("Поиск билетов")
    @Description("Проверка поиска билетов без указания даты")
    void testSearchWithMissingDate() {
        Assertions.assertAll(
                () -> assertTrue(mainPage.isPageOpened(), "Страница не открылась корректно"),
                () -> assertTrue(mainPage.isLogoDisplayed(), "Логотип отсутствует")
        );

        searchBlockPage.scrollToSearchBlock();
        Assertions.assertAll(
                () -> assertTrue(searchBlockPage.isSearchBlockDisplayed(), "Блок поиска билетов не отображается"),
                () -> assertTrue(searchBlockPage.areFieldsDisplayed(), "Не все поля поиска отображаются")
        );

        searchBlockPage.enterFrom("Москва");
        searchBlockPage.enterTo("Санкт-Петербург");
        searchBlockPage.clickSearch();
    }

    @Test
    @Order(3)
    @Feature("Управление бронированием")
    @Description("Проверка страницы управления бронированием с некорректными данными")
    void testBookingManagement() {
        Assertions.assertAll(
                () -> assertTrue(mainPage.isPageOpened(), "Страница не открылась корректно"),
                () -> assertTrue(mainPage.isLogoDisplayed(), "Логотип отсутствует")
        );

        bookingManagementPage.goToBookingManagement();
        Assertions.assertAll(
                () -> assertTrue(bookingManagementPage.isOrderNumberFieldDisplayed(), "Поле 'Номер заказа' не отображается"),
                () -> assertTrue(bookingManagementPage.isLastNameFieldDisplayed(), "Поле 'Фамилия клиента' не отображается"),
                () -> assertTrue(bookingManagementPage.isSearchButtonDisplayed(), "Кнопка 'Поиск' не отображается")
        );

        bookingManagementPage.enterOrderNumber("XXXXXX");
        bookingManagementPage.enterLastName("Qwerty");
        bookingManagementPage.clickSearchButton();
        assertTrue(bookingManagementPage.isErrorMessageDisplayed(), "Сообщение об ошибке не отображается");
    }

    @Test
    @Order(4)
    @Feature("Проверка падения теста")
    @Description("Этот тест всегда будет провален для демонстрации отчета Allure")
    void testFailingTest() {
        Assertions.fail("Этот тест должен упасть");
    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
    }
}
