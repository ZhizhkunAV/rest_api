package scecifications;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class SpecForAllTests {
    public static RequestSpecification getrequestspecification = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/user/2");

    public static ResponseSpecification getresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();


    public static RequestSpecification putrequestspecification = with()

            .contentType(JSON)
            .log().uri()
            .log().headers()
            .baseUri("https://reqres.in")
            .basePath("/api/user/2");


    public static ResponseSpecification putresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification postrequestspecification = with()

            .contentType(JSON)
            .log().uri()
            .log().headers()
            .baseUri("https://reqres.in")
            .basePath("/api/user");

    public static ResponseSpecification postresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();

    public static RequestSpecification delrequestspecification = with()

            .log().uri()
            .log().headers()
            .baseUri("https://reqres.in")
            .basePath("/api/user/2");

    public static ResponseSpecification delresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();

    public static RequestSpecification twopostrequestspecification = with()
            .contentType(JSON)
            .log().uri()
            .log().headers()
            .baseUri("https://reqres.in")
            .basePath("/api/login");

    public static ResponseSpecification twopostresponsespecification = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .log(HEADERS)
            .build();
}