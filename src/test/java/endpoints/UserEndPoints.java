package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import payload.User;
import utilities.GlobalVariables;

import static io.restassured.RestAssured.*;

public class UserEndPoints {

    public static Response createUser(User payload) {
        return given()
                .header(GlobalVariables.HEADER_KEY_CONTENT_TYPE,GlobalVariables.HEADER_VALUE_CONTENT_TYPE)
                .body(payload)
                .when()
                .post(Routes.CREATE_ADMIN_URL);
    }





}
