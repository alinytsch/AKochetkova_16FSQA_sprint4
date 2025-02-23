package ru.yandex.scooter.pageobjects;

import org.openqa.selenium.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ScooterOrderPage {
    private WebDriver driver;

    // Объявления элементов
    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private By confirmHeader = By.xpath(".//button[text()='Посмотреть статус']");
    private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalTimeField = By.className("Dropdown-placeholder");
    private final By rentalTime = By.xpath(".//*[(@role ='option' and text()='трое суток')]");
    private final By checkBoxColourBlackPearl = By.xpath(".//input[@id='black']");
    private final By checkBoxColourGreyDespair = By.xpath(".//input[@id='grey']");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");

    // Добавление недостающей переменной orderNextButton
    private By orderNextButton = By.xpath(".//button[text()='Далее']"); // Пример XPath для кнопки "Далее"

    // Конструктор
    public ScooterOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для работы с элементами страницы
    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }

    public String getConfirmHeader() {
        return driver.findElement(confirmHeader).getText();
    }

    // Исправление метода с использованием assertThat и is()
    public void isPageOpen(String headerText, String expectedText) {
        assertThat(headerText, is(expectedText)); // Исправлено использование is()
    }

    public void acceptCookieButtonClick() {
        driver.findElement(acceptCookieButton).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setSubway(String subway) {
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='" + subway + "']")).click();
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }

    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalTimeField).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[text()='" + color + "']")).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    // Метод для клика по кнопке "Далее"
    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).click();
    }

    public void clickOrderCreateButton() {
        driver.findElement(orderCreateButton).click();
    }

    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }
}
