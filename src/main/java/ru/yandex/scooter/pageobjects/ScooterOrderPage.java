package ru.yandex.scooter.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ScooterOrderPage {
    private WebDriver driver;

    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private By confirmHeader = By.xpath(".//button[contains(text(),'Посмотреть статус')]");
    private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By rentalTimeField = By.className("Dropdown-placeholder");
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");
    private By orderNextButton = By.xpath(".//button[text()='Далее']");

    private By orderButtonYes = By.xpath(".//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    private By modalOrderWindow = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public ScooterOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }

    public String getConfirmHeader() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmHeader));
        return driver.findElement(confirmHeader).getText();
    }

    public void waitForOrderConfirmation() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalOrderWindow));
    }

    public boolean isModalOrderWindowDisplayed() {
        return driver.findElement(modalOrderWindow).isDisplayed();
    }

    public void isPageOpen(String headerText, String expectedText) {
        assertThat(headerText, is(expectedText));
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
        WebElement dateInput = driver.findElement(dateField);
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
    }

    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalTimeField).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).click();
    }

    public void clickOrderCreateButton() {
        driver.findElement(orderCreateButton).click();
    }

    public void clickOrderButtonYes() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(orderButtonYes));
        confirmButton.click();
    }
}
