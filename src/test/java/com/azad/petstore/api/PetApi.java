package com.azad.petstore.api;

import com.azad.petstore.config.TestConfig;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetApi {

    public Response createPet(Map<String, Object> pet) {

        return given()
                .baseUri(TestConfig.PETSTORE_BASE_URL)
                .contentType("application/json")
                .accept("application/json")
                .body(pet)
                .log().all()

                .when()
                .post("/pet");
    }

    public Response getPet(int petId) {

        return given()
                .baseUri(TestConfig.PETSTORE_BASE_URL)
                .accept("application/json")
                .log().all()

                .when()
                .get("/pet/{petId}", petId);
    }

    public Response updatePet(Map<String, Object> pet) {

        return given()
                .baseUri(TestConfig.PETSTORE_BASE_URL)
                .contentType("application/json")
                .accept("application/json")
                .body(pet)
                .log().all()

                .when()
                .put("/pet");
    }

    public Response deletePet(int petId) {

        return given()
                .baseUri(TestConfig.PETSTORE_BASE_URL)
                .accept("application/json")
                .log().all()

                .when()
                .delete("/pet/{petId}", petId);
    }
}