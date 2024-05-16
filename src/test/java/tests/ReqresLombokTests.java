package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Тесты по тестированию платформы https://reqres.in/")
public class ReqresLombokTests {

    @DisplayName("Метод Get - тест на наличие email и успешность получения верного кода ответа(код 200)")
    @Test
    void checkEmailAndStatusCodePositiveTest() {
        GetResponseLombok responsetwo =
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .get("https://reqres.in/api/users/2")
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .statusCode(200)
                        .extract().as((Type) GetResponseLombok.class);

        assertThat(responsetwo.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
        assertThat(responsetwo.getData().getFirst_name()).isEqualTo("Janet");
        assertThat(responsetwo.getData().getLast_name()).isEqualTo("Weaver");
    }

    @DisplayName("Метод Put - тест на update уже имеющейся сущности(name,job) и успешность получения верного кода ответа(код 200)")
    @Test
    void successfulLoginTest() {
        PutRequestLombok requestLombok = new PutRequestLombok();
        requestLombok.setName("morpheus");
        requestLombok.setJob("zion resident");

        PutResponseLombok response = given()
                .body(requestLombok)
                .contentType(JSON)
                .log().uri()
                .log().headers()
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(PutResponseLombok.class);

        assertEquals("morpheus", response.getName());
        assertEquals("zion resident", response.getJob());

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("zion resident");
    }

    @DisplayName("Метод Post - тест на создание нового пользователя(name,job) и успешность получения верного кода ответа(код 200)")
    @Test
    void successfulCreatedUserTest() {
        PostRequestLombok postRequestLombok = new PostRequestLombok();
        postRequestLombok.setName("morpheus");
        postRequestLombok.setJob("leader");

        PostResponseLombok response = given()
                .body(postRequestLombok)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().headers()
                .log().body()
                .statusCode(201)
                .extract().as(PostResponseLombok.class);

        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());

        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
    }

    @DisplayName("Метод Delete - тест на удаление пользователя и успешность получения верного кода ответа(код 204)")
    @Test
    void deleteUserAndStatusCodeTest() {
        given()
                .log().uri()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .log().headers()
                .statusCode(204);
    }

    @DisplayName("Метод Post - тест на не валюдную авторизацию пользователя и успешность получения верного кода ответа(код 400)")
    @Test
    void loginUserUnsuccessfulTest() {

        TwoPostRequestLombok twopostRequestLombok = new TwoPostRequestLombok();
        twopostRequestLombok.setEmail("peter@klaven");


        TwoPostResponseLombok response = given()
                .body(twopostRequestLombok)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .log().headers()
                .statusCode(400)
                .extract().as(TwoPostResponseLombok.class);

        assertThat(response.getError()).isEqualTo("Missing password");
    }
}