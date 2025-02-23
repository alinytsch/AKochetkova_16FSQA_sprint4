package ru.yandex.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.scooter.pageobjects.ScooterMainPage;
import ru.yandex.scooter.pageobjects.ScooterOrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address, String subway, String phoneNumber, String date, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    // Статический метод, который возвращает набор параметров для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Агафон", "Петров", "г. Москва, ул. Б.Тульская, д. 15", "Тульская", "89999999999", "27-02-2025", "трое суток", "grey", "Позвоните, пожалуйста, заранее" },
                { "Аглая", "Иванова", "ул. Маяковская, д. 13, кв. 23", "Маяковская", "+79778886644", "01-03-2025", "трое суток", "black", "-" }
        });
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/alinytsch/WebDriver/bin/chrome-mac-arm64/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void OrderPositiveTest() {
        ScooterMainPage mainPage = new ScooterMainPage(driver);
        mainPage.clickHeaderOrderButton();

        ScooterOrderPage orderPage = new ScooterOrderPage(driver);
        orderPage.acceptCookieButtonClick();

        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.setSubway(subway);
        orderPage.setPhoneNumber(phoneNumber);
        orderPage.clickOrderNextButton();
        orderPage.setDate(date);
        orderPage.setRentalPeriod(rentalPeriod);
        orderPage.setColor(color); // Убедитесь, что цвет передается как строка "grey" или "black"
        orderPage.setComment(comment);
        orderPage.clickOrderCreateButton();
        orderPage.clickOrderConfirmButton();

        // Проверка текста заголовка подтверждения
        String confirmHeaderText = orderPage.getConfirmHeader();
        String expectedConfirmHeader = "Посмотреть статус"; // Замените на правильный текст, если нужно
        assert confirmHeaderText.equals(expectedConfirmHeader) : "Expected: " + expectedConfirmHeader + ", but got: " + confirmHeaderText;
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
