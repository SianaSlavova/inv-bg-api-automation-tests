package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ItemAPI {
    private static final String ENDPOINT = "/items";
    private static final String BASE_URI = "https://api.inv.bg";
    private static final String BASE_PATH = "/v3";
    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmaXJtSUQiOjExMDE3OSwidXNlcklEIjoxLCJpYXQiOjE3MjE3MjUwMDYsImV4cCI6MTcyMTgxMTQwNiwic2NvcGUiOlsiaHVtYW4iLCJhZG1pbiJdfQ.376_sEsXm_jalV9uNXcy1PhMMOXdbvvGWRwYTkycgj0";


    public ItemAPI(String token) {
        this.token = token;
    }

    public static Response getItems() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .log().all()
                .auth().oauth2(token)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .when()
                .get(ENDPOINT)
                .prettyPeek();

    }

    public Response createItem() {
        return null;
    }

    public static void main(String[] args) {
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.getItems();
    }
}
