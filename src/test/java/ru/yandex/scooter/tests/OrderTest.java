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

import static org.junit.Assert.assertEquals;

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
    private final String comment;

    public OrderTest(String name, String surname, String address, String subway, String phoneNumber,
                     String date, String rentalPeriod, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Агафон", "Петров", "г. Москва, ул. Б.Тульская, д. 15", "Тульская", "89999999999",
                        "27-02-2025", "трое суток", "Позвоните заранее"},
                {"Аглая", "Иванова", "ул. Маяковская, д. 13, кв. 23", "Маяковская", "+79778886644",
                        "01-03-2025", "трое суток", "-"}
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
        orderPage.setComment(comment);
        orderPage.clickOrderCreateButton();

        // Дождаться и кликнуть на кнопку "Да"
        orderPage.clickOrderButtonYes();

        // Дождаться появления модального окна
        orderPage.waitForOrderConfirmation();

        // Проверить заголовок подтверждения заказа
        assertEquals("Заказ оформлен", orderPage.getConfirmHeader());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
