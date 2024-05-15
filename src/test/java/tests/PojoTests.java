package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@DisplayName("Тесты по тестированию платформы https://reqres.in/")
public class PojoTests {

    @DisplayName("Метод Get - тест на наличие email и успешность получения верного кода ответа(код 200)")
    @Test
    void checkEmailAndStatusCodePositiveTest() {
        given()
                .log().uri()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"));
    }


    @DisplayName("Метод Put - тест на update уже имеющейся сущности(name,job) и успешность получения верного кода ответа(код 200)")
    @Test
    void successfulLoginTest() {
        String putData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .body(putData)
                .contentType(JSON)
                .log().uri()

                .when()
                .put("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @DisplayName("Метод Post - тест на создание нового пользователя(name,job) и успешность получения верного кода ответа(код 200)")
    @Test
    void successfulCreatedUserTest() {
        String postData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .body(postData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
        ;
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
                .statusCode(204);
    }

    @DisplayName("Метод Post - тест на не валюдную авторизацию пользователя и успешность получения верного кода ответа(код 400)")
    @Test
    void loginUserUnsuccessfulTest() {
        String postLoginData = "{\"email\": \"peter@klaven\"}";

        given()
                .body(postLoginData)
                .contentType(JSON)
                .log().uri()

                .when()
                .post("https://reqres.in/api/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}

