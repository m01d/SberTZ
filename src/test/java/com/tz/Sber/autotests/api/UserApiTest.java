package com.tz.Sber.autotests.api;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Epic("User API Tests")
@Feature("User Management")

public class UserApiTest {

    private String username = "testuser";
    private String userJson = "{ \"id\": 1, \"username\": \"testuser\", \"firstName\": \"Test\", \"lastName\": \"User\"" +
            ", \"email\": \"test@example.com\", \"password\": \"password123\", \"phone\": \"1234567890\", \"userStatus\": 1 }";
    private String updatedUserJson = "{ \"id\": 1, \"username\": \"testuser\", \"firstName\": \"Updated\", \"lastName\": \"User\"" +
            ", \"email\": \"updated@example.com\", \"password\": \"newpassword\", \"phone\": \"0987654321\", \"userStatus\": 1 }";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    @Description("Create new user")
    @Story("Create User")
    public void createUser() {
        given()
                .contentType("application/json")
                .body(userJson)
        .when()
                .post("/user")
        .then()
                .body(equalTo("{\"code\":200,\"type\":\"unknown\",\"message\":\"1\"}"));
    }

    @Test
    @Description("Update user information")
    @Story("Update User")
    public void updateUser() {
        given()
                .contentType("application/json")
                .body(updatedUserJson)
        .when()
                .put("/user/" + username)
        .then()
                .body(equalTo("{\"code\":200,\"type\":\"unknown\",\"message\":\"1\"}"));
    }

    @Test
    @Description("Get user information")
    @Story("Get User")
    public void getUser() {
        given()
                .pathParam("username", username)
                .when()
                .get("/user/{username}")
                .then()
                .body("firstName", equalTo("Updated"));
    }

    @Test
    @Description("Delete user")
    @Story("Delete User")
    public void deleteUser() {
        given()
                .pathParam("username", username)
                .when()
                .delete("/user/{username}")
                .then()
                .body(equalTo("{\"code\":200,\"type\":\"unknown\",\"message\":\"testuser\"}"));
    }



}
