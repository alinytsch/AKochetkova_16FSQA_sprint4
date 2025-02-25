package ru.yandex.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.scooter.pageobjects.ScooterMainPage;
import static ru.yandex.scooter.pageobjects.Resources.*;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class QuestionTest {
    private WebDriver driver;

    private final int questionIndex;
    private final String expectedAnswer;

    public QuestionTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, ANSWER1_TEXT},
                {2, ANSWER2_TEXT},
                {3, ANSWER3_TEXT},
                {4, ANSWER4_TEXT},
                {5, ANSWER5_TEXT},
                {6, ANSWER6_TEXT},
                {7, ANSWER7_TEXT},
                {8, ANSWER8_TEXT}
        });
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/alinytsch/WebDriver/bin/chrome-mac-arm64/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void FAQCorrectAnswerText() {
        ScooterMainPage mainPage = new ScooterMainPage(driver);
        mainPage.clickQuestion(questionIndex);
        mainPage.verifyAnswerText(questionIndex, expectedAnswer);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
