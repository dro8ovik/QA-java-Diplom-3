import api.Requests.RegisterUserRequest;
import api.Requests.LoginUserRequest;
import api.Responses.RegisteredUserResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Utils {

    public static void setReqSpec(String url, ContentType contentType) {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(contentType)
                .build();
    }

    public static void cleanTestUserData(RegisterUserRequest user) {
        given()
                .body(user)
                .when()
                .post(TestData.ENDPOINT_REGISTER);
        deleteUser(getRegisteredUser(user));
    }

    public static void deleteUser(RegisteredUserResponse user) {
        given()
                .header("authorization", user.getAccessToken())
                .body(user)
                .when()
                .delete(TestData.ENDPOINT_USER);
    }

    public static RegisteredUserResponse registerUser(LoginUserRequest user) {
        return given()
                .body(user)
                .when()
                .post(TestData.ENDPOINT_REGISTER)
                .then()
                .extract().as(RegisteredUserResponse.class);
    }

    public static RegisteredUserResponse getRegisteredUser(LoginUserRequest user) {
        return given()
                .body(user)
                .when()
                .post(TestData.ENDPOINT_LOGIN)
                .then()
                .extract().as(RegisteredUserResponse.class);
    }


}
