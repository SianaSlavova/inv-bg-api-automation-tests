package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginPageTest {
    private static final String LOGIN_PAGE = "https://dhp2019.inv.bg/";
    private static final String EMAIL = "ss3917@abv.bg";
    private static final String PASSWORD = "0876811300";
    private  WebDriver driver;

    @BeforeEach
    public void beforeEachTest(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(LOGIN_PAGE);
    }

    @AfterEach
    public void afterEachTest(){
        driver.quit();
    }

    @Test
    @Tag("ui")
    @DisplayName("Can login valid credentials")
    public void canLoginWithValidCredentials(){
        WebElement heading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.sendKeys(EMAIL);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys(PASSWORD);
        WebElement loginButton = driver.findElement(By.cssSelector(".selenium-submit-button"));
        loginButton.click();
        WebElement userPanelHeader = driver.findElement(By.xpath("//div[@class='selenium-button-userpanel']"));
        Assertions.assertEquals(EMAIL, userPanelHeader.getText());
    }

    @Test
    @Tag("ui")
    @DisplayName("Cant login with invalid credentials")
    public void cantLoginWithInvalidCredentials(){
        WebElement heading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.sendKeys(EMAIL);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("08768130000");
        WebElement loginButton = driver.findElement(By.cssSelector(".selenium-submit-button"));
        loginButton.click();
        WebElement loginError = driver.findElement(By.cssSelector(".selenium-error-block"));
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", loginError.getText().strip());
    }

    @Test
    @Tag("ui")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials(){
        WebElement heading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
        WebElement loginButton = driver.findElement(By.cssSelector(".selenium-submit-button"));
        loginButton.click();
        WebElement loginError = driver.findElement(By.cssSelector(".selenium-error-block"));
        Assertions.assertEquals("Моля, попълнете вашия email", loginError.getText().strip());
    }

    @Test
    @Tag("ui")
    @DisplayName("Can login and logout")
    public void canLoginAndLogout(){
        WebElement heading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.sendKeys(EMAIL);
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys(PASSWORD);
        WebElement loginButton = driver.findElement(By.cssSelector(".selenium-submit-button"));
        loginButton.click();
        WebElement userPanelHeader = driver.findElement(By.xpath("//div[@class='selenium-button-userpanel']"));
        Assertions.assertEquals(EMAIL, userPanelHeader.getText());
        userPanelHeader.click();
        WebElement logoutLink = driver.findElement(By.cssSelector(".selenium-button-logout"));
        logoutLink.click();
        WebElement logoutSuccess = driver.findElement(By.id("okmsg"));
        Assertions.assertEquals("Вие излязохте от акаунта си.", logoutSuccess.getText().strip());
        heading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
    }

}
