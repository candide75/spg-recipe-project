package com.example.sfgrecipeproject.controllers;

import com.example.sfgrecipeproject.domain.Recipe;
import com.example.sfgrecipeproject.services.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeController = new RecipeController(recipeService);
    }

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipes/index"));
    }

    @Test
    void getRecipes() {
        //given
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Recipe 1");
        recipes.add(recipe1);
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Recipe 2");
        recipes.add(recipe2);

        //when
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //then
        Assertions.assertEquals("/recipes/index", recipeController.getRecipes(model));

        Mockito.verify(model, Mockito.times(1)).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
        Set<Recipe> setInController = argumentCaptor.getValue();
        Assertions.assertEquals(2, setInController.size());
    }
}