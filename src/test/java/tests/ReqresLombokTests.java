package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import java.lang.reflect.Type;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static scecifications.SpecForAllTests.*;


@DisplayName("Тесты по тестированию платформы https://reqres.in/")
public class ReqresLombokTests  extends TestBase {

    @DisplayName("Метод Get - наличие email и успешность получения верного кода ответа(код 200)")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Tag("all")
    @Test
    void checkEmailAndStatusCodePositiveTest() {
        GetResponseLombok responsetwo =
                step("Отправка  Get запроса", () ->
                        given(getrequestspecification)
                                .get("")
                                .then()
                                .spec(getresponsespecification)
                                .extract().as((Type) GetResponseLombok.class)
                );

        step("Проверка полученного ответа на наличие определенных данных", () -> {

                    assertThat(responsetwo.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
                    assertThat(responsetwo.getData().getFirst_name()).isEqualTo("Janet");
                    assertThat(responsetwo.getData().getLast_name()).isEqualTo("Weaver");
                }
        );
    }

    @DisplayName("Метод Put - update уже имеющейся сущности(name,job) и успешность получения верного кода ответа(код 200)")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Tag("all")
    @Test
    void successfulLoginTest() {
        PutRequestLombok requestLombok = new PutRequestLombok();
        requestLombok.setName("morpheus");
        requestLombok.setJob("zion resident");

        PutResponseLombok response =
                step("Отправка  Put запроса", () ->
                        given(putrequestspecification)
                                .body(requestLombok)
                                .when()
                                .put("")
                                .then()
                                .spec(putresponsespecification)
                                .extract().as(PutResponseLombok.class)
                );

        step("Проверка полученного ответа на наличие определенных данных - Name и Job", () -> {
                    assertEquals("morpheus", response.getName());
                    assertEquals("zion resident", response.getJob());

                    assertThat(response.getName()).isEqualTo("morpheus");
                    assertThat(response.getJob()).isEqualTo("zion resident");
                }
        );
    }

    @DisplayName("Метод Post - создание нового пользователя(name,job) и успешность получения верного кода ответа(код 200)")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Tag("all")
    @Test
    void successfulCreatedUserTest() {
        PostRequestLombok postRequestLombok = new PostRequestLombok();
        postRequestLombok.setName("morpheus");
        postRequestLombok.setJob("leader");

        PostResponseLombok response =
                step("Отправка  Post запроса", () ->
                        given(postrequestspecification)
                                .body(postRequestLombok)
                                .when()
                                .post("")
                                .then()
                                .spec(postresponsespecification)
                                .extract().as(PostResponseLombok.class)
                );

        step("Проверка полученного ответа на наличие определенных данных - Name и Job", () -> {

//                    assertEquals("morpheus", response.getName()); второй вариант
//                    assertEquals("leader", response.getJob());
                    assertThat(response.getName()).isEqualTo("morpheus");
                    assertThat(response.getJob()).isEqualTo("leader");
                }
        );
    }

    @DisplayName("Метод Delete - удаление пользователя и успешность получения верного кода ответа(код 204)")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Tag("all")
    @Test
    void deleteUserAndStatusCodeTest() {
        step("Отправка  Post запроса", () ->
                given(delrequestspecification)
                        .delete("")
                        .then()
                        .spec(delresponsespecification)
        );
    }

    @DisplayName("Метод Post - не валюдную авторизацию пользователя и успешность получения верного кода ответа(код 400)")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Tag("all")
    @Test
    void loginUserUnsuccessfulTest() {
        TwoPostRequestLombok twopostRequestLombok = new TwoPostRequestLombok();
        twopostRequestLombok.setEmail("peter@klaven");

        TwoPostResponseLombok response =
                step("Отправка  Post запроса", () ->
                        given(twopostrequestspecification)
                                .body(twopostRequestLombok)
                                .when()
                                .post("")
                                .then()
                                .spec(twopostresponsespecification)
                                .extract().as(TwoPostResponseLombok.class)
                );
        step("Проверка полученного ответа на наличие определенного текста ошибки - Missing password", () -> {
                    assertThat(response.getError()).isEqualTo("Missing password");
                }
        );
    }
}