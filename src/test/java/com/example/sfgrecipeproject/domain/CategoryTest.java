package com.example.sfgrecipeproject.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    void getDescription() {
        String description = "This is the description!";
        category.setDescription(description);
        Assertions.assertEquals(description, category.getDescription());
    }

    @Test
    void getRecipes() {
    }
}