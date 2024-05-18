package scecifications;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;


public class SpecForAllTests {
    public static RequestSpecification getrequestspecification = with()
            .filter(new AllureRestAssured())
            .log().uri()
            .log().headers();

    public static ResponseSpecification getresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();


    public static RequestSpecification putrequestspecification = with()
            .filter(new AllureRestAssured())
            .contentType(JSON)
            .log().uri()
            .log().headers()
            .basePath("/api/user/2");


    public static ResponseSpecification putresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification postrequestspecification = with()
            .filter(new AllureRestAssured())
            .contentType(JSON)
            .log().uri()
            .log().headers()
            .basePath("/api/user");

    public static ResponseSpecification postresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();

    public static RequestSpecification delrequestspecification = with()
            .filter(new AllureRestAssured())
            .log().uri()
            .log().headers()
            .basePath("/api/user/2");

    public static ResponseSpecification delresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();

    public static RequestSpecification twopostrequestspecification = with()
            .filter(new AllureRestAssured())
            .contentType(JSON)
            .log().uri()
            .log().headers()
            .basePath("/api/login");

    public static ResponseSpecification twopostresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();
}