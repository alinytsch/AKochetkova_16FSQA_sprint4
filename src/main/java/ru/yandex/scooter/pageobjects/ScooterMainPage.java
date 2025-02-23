package ru.yandex.scooter.pageobjects;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;

public class ScooterMainPage {
    private WebDriver driver;

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By question(int index) {
        return By.xpath(".//div[@class='accordion__item'][" + index + "]");
    }

    private By answer(int index) {
        return By.id("accordion__panel-" + (index - 1));
    }

    private By headerOrderButton = By.xpath(".//button[text()='Заказать'][1]");
    private By pageOrderButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button");

    public void clickQuestion(int index) {
        WebDriverWait wait = new WebDriverWait(driver, 10); // Ожидание 10 секунд
        WebElement questionElement = driver.findElement(question(index));

        // Явное ожидание, пока элемент станет видимым
        wait.until(ExpectedConditions.visibilityOf(questionElement));
        // Ожидаем, пока элемент станет кликабельным
        wait.until(ExpectedConditions.elementToBeClickable(questionElement));

        // Прокрутка до элемента, если он не в пределах видимой области
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);

        // После того как элемент стал видимым и кликабельным, кликаем
        questionElement.click();
    }

    public String getAnswerText(int index) {
        return driver.findElement(answer(index)).getText();
    }

    public void verifyAnswerText(int index, String expectedText) {
        MatcherAssert.assertThat(getAnswerText(index), is(expectedText));
    }

    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    public void clickPageOrderButton() {
        WebElement bigButton = driver.findElement(pageOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        bigButton.click();
    }
}
