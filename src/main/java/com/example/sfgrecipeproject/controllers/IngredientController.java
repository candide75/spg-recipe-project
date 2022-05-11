package com.example.sfgrecipeproject.controllers;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
public class IngredientController {

    RecipeService recipeService;

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String listIngredients(Model model, @PathVariable String id) {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe", recipeCommand);
        return "recipe/ingredient/list";
    }
}
