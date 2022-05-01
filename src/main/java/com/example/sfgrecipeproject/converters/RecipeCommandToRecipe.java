package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    CategoryCommandToCategory categoryCommandToCategory;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryCommandToCategory, IngredientCommandToIngredient ingredientCommandToIngredient, NotesCommandToNotes notesCommandToNotes) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.notesCommandToNotes = notesCommandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setId(source.getId());
        recipe.getCategories().addAll(
                source.getCategories().stream().map(categoryCommandToCategory::convert).collect(Collectors.toSet())
        );
        recipe.setCookTime(source.getCookTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setImage(source.getImage());
        recipe.getIngredients().addAll(
                source.getIngredients().stream().map(ingredientCommandToIngredient::convert).collect(Collectors.toSet())
        );
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        recipe.setPrepTime(source.getPrepTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());

        return recipe;
    }
}
