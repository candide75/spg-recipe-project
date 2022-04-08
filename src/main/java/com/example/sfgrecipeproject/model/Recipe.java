package com.example.sfgrecipeproject.model;

import java.util.Set;

public class Recipe {

    private String name;
    private Set<Ingredient> ingredients;
    private Set<String> steps;

    public Recipe(String name, Set<Ingredient> ingredients, Set<String> steps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<String> getSteps() {
        return steps;
    }

    public void setSteps(Set<String> steps) {
        this.steps = steps;
    }
}
