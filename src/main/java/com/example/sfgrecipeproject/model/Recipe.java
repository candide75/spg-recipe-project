package com.example.sfgrecipeproject.model;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Recipe extends BaseEntity{

    private String name;
    private Set<Ingredient> ingredients;
    @ElementCollection
    private Set<String> steps;

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "recipe")
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
