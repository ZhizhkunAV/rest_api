package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specifications.SpecForAllTests.*;


@DisplayName("Platform testing tests https://reqres.in/")
@Owner("ZhizhkunAV")
public class ReqresTests extends TestBase {

    @DisplayName("Get method - user's e-mail presence")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({
            @Tag("userData"),
            @Tag("all")
    })
    @Test
    void checkEmailAndStatusCodePositiveTest() {
        UserModel response =
                step("Sending a Get request", () ->
                        given(requestSpec)
                                .get("/users/2")
                                .then()
                                .spec(code200ResponseSpecification)
                                .extract().as(UserModel.class)
                );

        step("Checking the response received for certain data", () -> {
                    assertThat(response.getData().getEmail()).isEqualTo("janet.weaver@reqres.in");
                    assertThat(response.getData().getFirstName()).isEqualTo("Janet");
                    assertThat(response.getData().getLastName()).isEqualTo("Weaver");
                }
        );
    }


    @DisplayName("Put method - change user data")
    @Tags({
            @Tag("account"),
            @Tag("all")
    })
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void successfulLoginTest() {
        Update update = new Update();
        update.setName("morpheus");
        update.setJob("zion resident");

        Update response =
                step("Sending Put request", () ->
                        given(requestSpec)
                                .body(update)
                                .when()
                                .put("/user/2")
                                .then()
                                .spec(code200ResponseSpecification)
                                .extract().as(Update.class)
                );

        step("Checking the received response for parameters - Name and Job", () -> {
                    assertEquals("morpheus", response.getName());
                    assertThat(response.getJob()).isEqualTo("zion resident");
                }
        );
    }

    @DisplayName("Post method - successful creation of a new user")
    @Tags({
            @Tag("account"),
            @Tag("all")
    })
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void successfulCreatedUserTest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("morpheus");
        userRequest.setJob("leader");

        UserResponse response =
                step("Sending Post request", () ->
                        given(requestSpec)
                                .body(userRequest)
                                .when()
                                .post("/user")
                                .then()
                                .spec(code201responseSpecification)
                                .extract().as(UserResponse.class)
                );

        step("Checking the received response for the presence of Name and Job parameters", () -> {
                    assertThat(response.getName()).isEqualTo("morpheus");
                    assertThat(response.getJob()).isEqualTo("leader");
                }
        );
    }


    @DisplayName("Delete method - successful deletion of a user")
    @Tags({
            @Tag("account"),
            @Tag("all")
    })
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void deleteUserAndStatusCodeTest() {
        step("Sending Post request", () ->
                given(requestSpec)
                        .delete("/user/2")
                        .then()
                        .spec(code204responSespecification)
        );
    }

    @DisplayName("Method Post - Check for unsuccessful user registration")
    @Tags({
            @Tag("userData"),
            @Tag("all")
    })
    @Severity(SeverityLevel.BLOCKER)
    @Test
    void loginUserUnsuccessfulTest() {
        LoginUnsuccessfulRequest loginUnsuccessfulRequest = new LoginUnsuccessfulRequest();
        loginUnsuccessfulRequest.setEmail("peter@klaven");

        LoginUnsuccessfulRespons response =
                step("Sending Post request", () ->
                        given(requestSpec)
                                .body(loginUnsuccessfulRequest)
                                .when()
                                .post("/login")
                                .then()
                                .spec(code400ResponseSpecification)
                                .extract().as(LoginUnsuccessfulRespons.class)
                );
        step("Checking the received response for error text - Missing password", () -> {
                    assertThat(response.getError()).isEqualTo("Missing password");
                }
        );
    }
}