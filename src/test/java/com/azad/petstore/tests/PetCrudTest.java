package com.azad.petstore.tests;

import com.azad.petstore.api.PetApi;
import com.azad.petstore.data.PetTestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PetCrudTest {

    private static final PetApi petApi = new PetApi();

    private static int petId;
    private static Map<String, Object> pet;

    @BeforeAll
    static void beforeAll() {
        pet = PetTestData.createPet();
    }

    @Test
    @Order(1)
    @DisplayName("POST Create a new Pet")
    void shouldCreatePet() {

        Response response = petApi.createPet(pet);

        response.then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(PetTestData.REQUEST_PET_ID))
                .body("name", equalTo("Kutta"))
                .body("status", equalTo("available"))
                .body("category.id", equalTo(1))
                .body("category.name", equalTo("Dogs"))
                .body("photoUrls[0]", equalTo("http://kutta.org"))
                .body("tags[0].id", equalTo(1))
                .body("tags[0].name", equalTo("fav"));

        petId = response.path("id");
    }

    @Test
    @Order(2)
    @DisplayName("GET Pet by ID")
    void shouldGetPetById() {

        Response response = petApi.getPet(petId);

        response.then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo("Kutta"))
                .body("status", equalTo("available"))
                .body("category.id", equalTo(1))
                .body("category.name", equalTo("Dogs"))
                .body("photoUrls[0]", equalTo("http://kutta.org"))
                .body("tags[0].id", equalTo(1))
                .body("tags[0].name", equalTo("fav"));
    }

    @Test
    @Order(3)
    @DisplayName("PUT Update an existing Pet")
    void shouldUpdatePet() {

        Map<String, Object> updatedPet =
                PetTestData.updatedPet(petId);

        Response response = petApi.updatePet(updatedPet);

        response.then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(petId))
                .body("name", equalTo("KuttaAzH"))
                .body("category.id", equalTo(1))
                .body("category.name", equalTo("DogsUpdated by AzH"))
                .body("photoUrls[0]", equalTo("http://kutta.org"))
                .body("tags[0].id", equalTo(1))
                .body("tags[0].name", equalTo("fav"))
                .body("status", equalTo("available"));
    }

    @Test
    @Order(4)
    @DisplayName("DELETE Existing pet by ID")
    void shouldDeletePet() {

        Response response = petApi.deletePet(petId);

        response.then()
                .log().all()
                .statusCode(200);

        Response getResponse = petApi.getPet(petId);

        getResponse.then()
                .log().all()
                .statusCode(404);
    }
}