package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(), new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()), new NotesToNotesCommand());
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);

        Category category = new Category();
        category.setId(CATEGORY_ID_VALUE);
        recipe.getCategories().add(category);
        recipe.setCookTime(COOK_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGREDIENT_ID_VALUE);
        recipe.getIngredients().add(ingredient);
        Notes notes = new Notes();
        notes.setId(NOTES_ID_VALUE);
        recipe.setNotes(notes);
        recipe.setPrepTime(PREP_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        // when
        RecipeCommand convert = converter.convert(recipe);

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