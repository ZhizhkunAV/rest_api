package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static scecifications.SpecForAllTests.*;


@DisplayName("Тесты по тестированию платформы https://reqres.in/")
public class ReqresLombokTests extends TestBase {

    @DisplayName("Метод Get - наличие email и успешность получения верного кода ответа(код 200)")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("alls")
    @Owner("ZhizhkunAV")
    @Test
    void checkEmailAndStatusCodePositiveTest() {
        CheckEmailResponseLombok checkresponse =
                step("Отправка  Get запроса", () ->
                        given(checkemeilrequestspecification)
                                .get("/users/2")
                                .then()
                                .spec(checkcode200responsespecification)
                                .extract().as(CheckEmailResponseLombok.class)
                );

        step("Проверка полученного ответа на наличие определенных данных", () -> {

                    assertThat(checkresponse.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
                    assertThat(checkresponse.getData().getFirstName()).isEqualTo("Janet");
                    assertThat(checkresponse.getData().getLastName()).isEqualTo("Weaver");
                }
        );
    }


    @DisplayName("Метод Put - update уже имеющейся сущности(name,job) и успешность получения верного кода ответа(код 200)")
    @Tag("alls")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Test
    void successfulLoginTest() {
        UdpadeLombok requestLombok = new UdpadeLombok();
        requestLombok.setName("morpheus");
        requestLombok.setJob("zion resident");

        UdpadeLombok response =
                step("Отправка  Put запроса", () ->
                        given(requestspecification)
                                .body(requestLombok)
                                .when()
                                .put("/user/2")
                                .then()
                                .spec(checkcode200responsespecification)
                                .extract().as(UdpadeLombok.class)
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
    @Tag("alls")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Test
    void successfulCreatedUserTest() {
        CreateUserRequestLombok createuserrequestlombok = new CreateUserRequestLombok();
        createuserrequestlombok.setName("morpheus");
        createuserrequestlombok.setJob("leader");

        CreateUserResponseLombok response =
                step("Отправка  Post запроса", () ->
                        given(requestspecification)
                                .body(createuserrequestlombok)
                                .when()
                                .post("/user")
                                .then()
                                .spec(responsespecification201code)
                                .extract().as(CreateUserResponseLombok.class)
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
    @Tag("alls")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Test
    void deleteUserAndStatusCodeTest() {
        step("Отправка  Post запроса", () ->
                given(checkemeilrequestspecification)
                        .delete("/user/2")
                        .then()
                        .spec(checkresponsespecification204code)
        );
    }

    @DisplayName("Метод Post - не валюдную авторизацию пользователя и успешность получения верного кода ответа(код 400)")
    @Tag("alls")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Test
    void loginUserUnsuccessfulTest() {
        LoginUnsuccessfulRequestLombok loginunsuccessfulrequestlombok = new LoginUnsuccessfulRequestLombok();
        loginunsuccessfulrequestlombok.setEmail("peter@klaven");

        LoginUnsuccessfulResponseLombok response =
                step("Отправка  Post запроса", () ->
                        given(requestspecification)
                                .body(loginunsuccessfulrequestlombok)
                                .when()
                                .post("/login")
                                .then()
                                .spec(checkresponsespecification400code)
                                .extract().as(LoginUnsuccessfulResponseLombok.class)
                );
        step("Проверка полученного ответа на наличие определенного текста ошибки - Missing password", () -> {
                    assertThat(response.getError()).isEqualTo("Missing password");
                }
        );
    }
}