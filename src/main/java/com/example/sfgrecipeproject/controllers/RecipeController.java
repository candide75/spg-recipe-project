package com.example.sfgrecipeproject.controllers;

import com.example.sfgrecipeproject.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @RequestMapping(value = {"/recipes/list"})
    public String getRecipes(Model model) {
        log.debug("Getting recipe list (controller)");
        model.addAttribute("recipes", recipeService.getRecipes());
        return "/recipes/index";
    }

    @RequestMapping("recipe/show/{id}")
    public String showById(Model model, @PathVariable Long id) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "/recipe/show";
    }
}