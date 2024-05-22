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
public class ReqresWithLombokTests extends TestBase {

    @DisplayName("Метод Get - наличие email и успешность получения верного кода ответа(код 200)")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("alls")
    @Owner("ZhizhkunAV")
    @Test
    void checkEmailAndStatusCodePositiveTest() {
        UserModel response =
                step("Отправка  Get запроса", () ->
                        given(requestSpecifications)
                                .get("/users/2")
                                .then()
                                .spec(code200ResponseSpecification)
                                .extract().as(UserModel.class)
                );

        step("Проверка полученного ответа на наличие определенных данных", () -> {

                    assertThat(response.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
                    assertThat(response.getData().getFirstName()).isEqualTo("Janet");
                    assertThat(response.getData().getLastName()).isEqualTo("Weaver");
                }
        );
    }


    @DisplayName("Метод Put - update уже имеющейся сущности(name,job) и успешность получения верного кода ответа(код 200)")
    @Tag("alls")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Test
    void successfulLoginTest() {
        Udpade update = new Udpade();
        update.setName("morpheus");
        update.setJob("zion resident");

        Udpade response =
                step("Отправка  Put запроса", () ->
                        given(requestSpecWithoutBody)
                                .body(update)
                                .when()
                                .put("/user/2")
                                .then()
                                .spec(code200ResponseSpecification)
                                .extract().as(Udpade.class)
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
        UserRequest userRequest = new UserRequest();
        userRequest.setName("morpheus");
        userRequest.setJob("leader");

        UserResponse response =
                step("Отправка  Post запроса", () ->
                        given(requestSpecWithoutBody)
                                .body(userRequest)
                                .when()
                                .post("/user")
                                .then()
                                .spec(code201responseSpecification)
                                .extract().as(UserResponse.class)
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
                given(requestSpecifications)
                        .delete("/user/2")
                        .then()
                        .spec(code204responSespecification)
        );
    }

    @DisplayName("Метод Post - не валюдную авторизацию пользователя и успешность получения верного кода ответа(код 400)")
    @Tag("alls")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("ZhizhkunAV")
    @Test
    void loginUserUnsuccessfulTest() {
        LoginUnsuccessfulRequest loginUnsuccessfulRequest = new LoginUnsuccessfulRequest();
        loginUnsuccessfulRequest.setEmail("peter@klaven");

        LoginUnsuccessfulRespons response =
                step("Отправка  Post запроса", () ->
                        given(requestSpecWithoutBody)
                                .body(loginUnsuccessfulRequest)
                                .when()
                                .post("/login")
                                .then()
                                .spec(code400ResponseSpecification)
                                .extract().as(LoginUnsuccessfulRespons.class)
                );
        step("Проверка полученного ответа на наличие определенного текста ошибки - Missing password", () -> {
                    assertThat(response.getError()).isEqualTo("Missing password");
                }
        );
    }
}