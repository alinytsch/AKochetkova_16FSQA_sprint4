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

    private By[] questions = {
            By.xpath(".//div[@class='accordion__item'][1]"),
            By.xpath(".//div[@class='accordion__item'][2]"),
            By.xpath(".//div[@class='accordion__item'][3]"),
            By.xpath(".//div[@class='accordion__item'][4]"),
            By.xpath(".//div[@class='accordion__item'][5]"),
            By.xpath(".//div[@class='accordion__item'][6]"),
            By.xpath(".//div[@class='accordion__item'][7]"),
            By.xpath(".//div[@class='accordion__item'][8]")
    };

    private By[] answers = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    private By headerOrderButton = By.xpath(".//button[text()='Заказать'][1]");
    private By pageOrderButton = By.xpath(".//div[contains(@class,'Home_FinishButton')]/button");

    public void clickQuestion(int index) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement questionElement = driver.findElement(questions[index - 1]);

        wait.until(ExpectedConditions.visibilityOf(questionElement));
        wait.until(ExpectedConditions.elementToBeClickable(questionElement));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);
        questionElement.click();
    }

    public String getAnswerText(int index) {
        return driver.findElement(answers[index - 1]).getText();
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
