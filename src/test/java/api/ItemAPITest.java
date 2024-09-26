package api;

import dto.Credential;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ItemAPITest
{

    private static final String EMAIL = "ss3917@abv.bg";
    private static final String PASSWORD = "0876811300";
    private static final String DOMAIN = "DHP2019";
    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmaXJtSUQiOjExMDE3OSwidXNlcklEIjoxLCJpYXQiOjE3MjE3MjUwMDYsImV4cCI6MTcyMTgxMTQwNiwic2NvcGUiOlsiaHVtYW4iLCJhZG1pbiJdfQ.376_sEsXm_jalV9uNXcy1PhMMOXdbvvGWRwYTkycgj0";

    @Test
    @Tag("api")
    @DisplayName("Can get all items - rest")
    public void canGetAllItems(){
        //TODO: Implement the test using the available methods
        LoginAPI loginAPI = new LoginAPI();
        //Obtain token
        Credential sianaCredentials = new Credential(EMAIL, PASSWORD, DOMAIN);
        Response response = loginAPI.obtainToken(sianaCredentials);
        //Call getItems()
        ItemAPI itemAPI = new ItemAPI(token);
        Response itemsResponse = ItemAPI.getItems();
        //Assert status code
        Assertions.assertEquals(200, itemsResponse.statusCode());
        //Assert response contains valid body
        String responseBody = itemsResponse.getBody().jsonPath().prettyPrint();
        Assertions.assertFalse(responseBody.isEmpty());
    }
}
