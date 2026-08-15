package com.azad.petstore.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class PetTestData {

    private PetTestData() {
    }

    public static final int REQUEST_PET_ID = new Random().nextInt(100000);

    public static Map<String, Object> createPet() {
        Map < String, Object > category = new HashMap<>();
        category.put("id", "1");
        category.put("name", "Dogs");

        Map < String, Object > tag = new HashMap<>();
        tag.put("id", "1");
        tag.put("name", "fav");

        Map< String, Object > pet = new HashMap<>();
        pet.put("id", REQUEST_PET_ID);
        pet.put("category", category);
        pet.put("name", "Kutta");
        pet.put("photoUrls", List.of("http://kutta.org"));
        pet.put("tags", List.of(tag));
        pet.put("status", "available");

        return pet;
    }

    public static Map<String, Object> updatedPet(int petId) {
        Map < String, Object > category = new HashMap<>();
        category.put("id", "1");
        category.put("name", "DogsUpdated by AzH");

        Map < String, Object > tag = new HashMap<>();
        tag.put("id", "1");
        tag.put("name", "fav");

        Map< String, Object > pet = new HashMap<>();
        pet.put("id", petId);
        pet.put("category", category);
        pet.put("name", "KuttaAzH");

        pet.put("photoUrls", List.of("http://kutta.org"));
        pet.put("tags", List.of(tag));
        pet.put("status", "available");
        return pet;
    }


}