package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;


class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        ;
    }

    @BeforeEach
    void setUp() {
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestPositive1AllValidValue() {


        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+78987456321");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestPositive2DoubleSecondName() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();


        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestPositive3DoubleFirstName() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван-Сергей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);
    }

    //    @Test
//    void shouldTestPositive4ApostropheInSurname(){
//
//        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Д'Иванов Иван");
//        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
//        driver.findElement(By.cssSelector("span.checkbox__text")).click();
//        driver.findElement(By.cssSelector("span.button__text")).click();
//
//        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
//        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
//        assertEquals(expected, actual);
//    }
    @Test
    void shouldTestNegative1EnglishSecondName() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ivanov Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative2EnglishFirstName() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Ivan");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative3NumberAsSecondName() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("1233 Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative4NumberAsFirstName() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов 123");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79874561221");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative5NumberPhone() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+798745621");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative6CheckboxOff() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79031234567");
        driver.findElement(By.tagName("button")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative7NameOff() {

        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+798745621");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative8NumberOff() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("span.checkbox__text")).click();
        driver.findElement(By.cssSelector("span.button__text")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldTestNegative9EmptyForm() {

        driver.findElement(By.tagName("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

}