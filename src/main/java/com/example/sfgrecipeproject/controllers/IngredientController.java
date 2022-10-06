package com.example.sfgrecipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.services.IngredientService;
import com.example.sfgrecipeproject.services.RecipeService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IngredientController {

    RecipeService recipeService;
    IngredientService ingredientService;

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(Model model, @PathVariable String id) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";
    }
}
