package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.CategoryCommand;
import com.example.sfgrecipeproject.commands.IngredientCommand;
import com.example.sfgrecipeproject.commands.NotesCommand;
import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.domain.Difficulty;
import com.example.sfgrecipeproject.domain.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeCommandToRecipeTest {

    public static final long ID_VALUE = 1L;
    public static final long CATEGORY_ID_VALUE = 2L;
    public static final int COOK_TIME = 10;
    public static final String DESCRIPTION = "description";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final String DIRECTIONS = "directions";
    public static final long INGREDIENT_ID_VALUE = 3L;
    public static final long NOTES_ID_VALUE = 4L;
    public static final int PREP_TIME = 15;
    public static final int SERVINGS = 4;
    public static final String SOURCE = "source";
    public static final String URL = "url";

    RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(), new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()), new NotesCommandToNotes());
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CATEGORY_ID_VALUE);
        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGREDIENT_ID_VALUE);
        recipeCommand.getIngredients().add(ingredientCommand);
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID_VALUE);
        recipeCommand.setNotes(notesCommand);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);

        // when
        Recipe convert = converter.convert(recipeCommand);

        // then
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(1, convert.getCategories().size());
        Assertions.assertEquals(CATEGORY_ID_VALUE, convert.getCategories().stream().findFirst().get().getId());
        Assertions.assertEquals(COOK_TIME, convert.getCookTime());
        Assertions.assertEquals(DESCRIPTION, convert.getDescription());
        Assertions.assertEquals(DIFFICULTY, convert.getDifficulty());
        Assertions.assertEquals(DIRECTIONS, convert.getDirections());
        Assertions.assertNotNull(convert.getIngredients());
        Assertions.assertEquals(1, convert.getIngredients().size());
        Assertions.assertEquals(INGREDIENT_ID_VALUE, convert.getIngredients().stream().findFirst().get().getId());
        Assertions.assertNotNull(convert.getNotes());
        Assertions.assertEquals(NOTES_ID_VALUE, convert.getNotes().getId());
        Assertions.assertEquals(PREP_TIME, convert.getPrepTime());
        Assertions.assertEquals(SERVINGS, convert.getServings());
        Assertions.assertEquals(SOURCE, convert.getSource());
        Assertions.assertEquals(URL, convert.getUrl());
    }
}