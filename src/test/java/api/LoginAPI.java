package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Credential;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginAPI {
    private static final String BASE_URI = "https://api.inv.bg";
    private static final String BASE_PATH = "/v3";
    private static final String ENDPOINT = "/login/token"; //resource path
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Response obtainToken(Credential credential){
        String body = GSON.toJson(credential);
        return RestAssured.given()
                .baseUri(BASE_URI)
                .log().all()
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "Mozilla")
                .when()
                .body(body)
                .post(ENDPOINT)
                .prettyPeek();
    }
}
