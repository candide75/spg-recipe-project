package com.example.sfgrecipeproject.controllers;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.domain.Recipe;
import com.example.sfgrecipeproject.repositories.RecipeRepository;
import com.example.sfgrecipeproject.services.RecipeService;

class RecipeControllerTest {

    public static final long ID = 1L;

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    Model model;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void getRecipes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipes/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipes"));
    }

    @Test
    public void showById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        Mockito.when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + ID + "/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void newRecipe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);

        Mockito.when(recipeService.saveRecipeCommand(Mockito.any(RecipeCommand.class))).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipe")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
                        .param("description", "some string"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipe/" + ID + "/show"));
    }

    @Test
    public void testGetUpdateView() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID);

        Mockito.when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + ID + "/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/recipeform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    void testMockMVC() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipes/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipes/index"));
    }

    @Test
    void getRecipesList() {
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

    @Test
    void getRecipeView() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        Mockito.when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipe);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        Assertions.assertEquals("/recipe/show", recipeController.showById(model, String.valueOf(ID)));

        Mockito.verify(model).addAttribute(Mockito.eq("recipe"), argumentCaptor.capture());
        Mockito.verify(recipeService).getRecipeById(Mockito.anyLong());

        Recipe captorRecipe = argumentCaptor.getValue();
        Assertions.assertEquals(ID, captorRecipe.getId());
    }

    @Test
    void getRecipeViewMockMVC() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(ID);

        Mockito.when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + ID + "/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/recipe/show"));
    }

    @Test
    void deleteByIdMockMVC() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/" + 3L + "/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/recipes/list"));
    }
}