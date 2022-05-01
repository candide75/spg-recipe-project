package com.example.sfgrecipeproject.services;

import com.example.sfgrecipeproject.commands.RecipeCommand;
import com.example.sfgrecipeproject.converters.RecipeCommandToRecipe;
import com.example.sfgrecipeproject.converters.RecipeToRecipeCommand;
import com.example.sfgrecipeproject.domain.Recipe;
import com.example.sfgrecipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String DESCRIPTION = "description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Transactional
    @Test
    public void testSaveOfDescription() {
        // given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        // when
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        // then
        Assertions.assertEquals(DESCRIPTION, savedRecipeCommand.getDescription());
        Assertions.assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        Assertions.assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        Assertions.assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}
