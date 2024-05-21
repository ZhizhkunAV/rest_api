package scecifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;


public class SpecForAllTests {
    public static RequestSpecification checkemeilrequestspecification = with()
            .filter(withCustomTemplates())
            .log().all();

    public static RequestSpecification requestspecification = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static ResponseSpecification checkcode200responsespecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification responsespecification201code = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();


    public static ResponseSpecification checkresponsespecification204code = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(ALL)
            .build();

    public static ResponseSpecification checkresponsespecification400code = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
}