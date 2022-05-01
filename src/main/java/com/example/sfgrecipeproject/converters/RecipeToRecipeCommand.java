package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    CategoryToCategoryCommand categoryToCategoryCommand;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand, IngredientToIngredientCommand ingredientToIngredientCommand, NotesToNotesCommand notesToNotesCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setId(source.getId());
        recipeCommand.getCategories().addAll(
                source.getCategories().stream().map(categoryToCategoryCommand::convert).collect(Collectors.toSet())
        );
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setImage(source.getImage());
        recipeCommand.getIngredients().addAll(
                source.getIngredients().stream().map(ingredientToIngredientCommand::convert).collect(Collectors.toSet())
        );
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());

        return recipeCommand;
    }
}
