package com.example.sfgrecipeproject.services;

import com.example.sfgrecipeproject.domain.Recipe;
import com.example.sfgrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet();
        recipesData.add(recipe);

        Mockito.lenient().when(recipeRepository.findAll()).thenReturn(recipesData);

        Assertions.assertEquals(recipeService.getRecipes().size(), 1);

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}