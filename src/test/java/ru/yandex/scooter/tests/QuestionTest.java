package ru.yandex.scooter.tests;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import ru.yandex.scooter.pageobjects.ScooterMainPage;
import static ru.yandex.scooter.pageobjects.Resources.*;

public class QuestionTest {
    private WebDriver driver;

    @Test
    public void FAQCorrectAnswerText() {
        System.setProperty("webdriver.chrome.driver", "/Users/alinytsch/WebDriver/bin/chrome-mac-arm64/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        ScooterMainPage mainPage = new ScooterMainPage(driver);

        for (int i = 1; i <= 8; i++) {
            mainPage.clickQuestion(i);
            mainPage.verifyAnswerText(i, getExpectedAnswerText(i));
        }
    }

    @After
    public void teardown() {
        driver.quit();
    }

    private String getExpectedAnswerText(int index) {
        switch (index) {
            case 1: return answer1Text;
            case 2: return answer2Text;
            case 3: return answer3Text;
            case 4: return answer4Text;
            case 5: return answer5Text;
            case 6: return answer6Text;
            case 7: return answer7Text;
            case 8: return answer8Text;
            default: throw new IllegalArgumentException("Invalid question index: " + index);
        }
    }
}
