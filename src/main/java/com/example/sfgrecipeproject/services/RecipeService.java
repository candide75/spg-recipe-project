package com.example.sfgrecipeproject.services;

import com.example.sfgrecipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
