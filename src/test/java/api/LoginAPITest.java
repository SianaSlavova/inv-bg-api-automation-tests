package api;

import dto.Credential;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginAPITest {
    private static final String EMAIL = "ss3917@abv.bg";
    private static final String PASSWORD = "0876811300";
    private static final String DOMAIN = "DHP2019";
    @Test
    @Tag("api")
    @DisplayName("Can login with valid credentials - rest")
    public void canLoginWithValidCredentials(){
        LoginAPI loginAPI = new LoginAPI();
        Credential sianaCredentials = new Credential(EMAIL, PASSWORD, DOMAIN);
        Response response = loginAPI.obtainToken(sianaCredentials);
        Assertions.assertEquals(200, response.statusCode());
        String token = response.body().jsonPath().get("token");
        Assertions.assertFalse(token.isEmpty(), "Token is empty but it should not be");
    }

    @Test
    @Tag("api")
    @DisplayName("Can't login with invalid credentials - rest")
    public void cantLoginWithInvalidCredentials(){
        LoginAPI loginAPI = new LoginAPI();
        Credential sianaCredentials = new Credential(EMAIL, "1111222", DOMAIN);
        Response response = loginAPI.obtainToken(sianaCredentials);
        Assertions.assertEquals(401, response.statusCode());
        String errorMessage = response.body().jsonPath().get("error");
        Assertions.assertEquals("Wrong username or password", errorMessage);
    }

    @Test
    @Tag("api")
    @DisplayName("Can't login with blank credentials - rest")
    public void cantLoginWithBlankCredentials(){
        LoginAPI loginAPI = new LoginAPI();
        Credential sianaCredentials = new Credential("", "", "");
        Response response = loginAPI.obtainToken(sianaCredentials);
        Assertions.assertEquals(401, response.statusCode());
        String errorMessage = response.body().jsonPath().get("error");
        Assertions.assertEquals("Firm not found", errorMessage);
    }

    @Test
    @Tag("api")
    @DisplayName("Can't login with blank body - rest")
    public void cantLoginWithBlankBody(){
        LoginAPI loginAPI = new LoginAPI();
        Credential sianaCredentials = new Credential(null, null, null);
        Response response = loginAPI.obtainToken(sianaCredentials);
        Assertions.assertEquals(400, response.statusCode());
    }
}
