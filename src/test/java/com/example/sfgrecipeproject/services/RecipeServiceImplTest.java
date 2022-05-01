package com.example.sfgrecipeproject.services;

import com.example.sfgrecipeproject.converters.RecipeCommandToRecipe;
import com.example.sfgrecipeproject.converters.RecipeToRecipeCommand;
import com.example.sfgrecipeproject.domain.Recipe;
import com.example.sfgrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet();
        recipesData.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipesData);

        Assertions.assertEquals(recipeService.getRecipes().size(), 1);

        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
        Mockito.verify(recipeRepository, Mockito.never()).findById(Mockito.anyLong());
    }

    @Test
    void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(optionalRecipe);

        Assertions.assertEquals(1L, recipeService.getRecipeById(1L).getId());

        Mockito.verify(recipeRepository).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.never()).findAll();
    }

    @Test
    void saveRecipeCommand() {
    }
}