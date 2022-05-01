package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.IngredientCommand;
import com.example.sfgrecipeproject.commands.UnitOfMeasureCommand;
import com.example.sfgrecipeproject.domain.Ingredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class IngredientCommandToIngredientTest {

    public static final Long ID_VALUE = 1L;
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "description";
    public static final Long UOM_ID_VALUE = 2L;

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID_VALUE);
        ingredientCommand.setUom(unitOfMeasureCommand);

        // when
        Ingredient convert = converter.convert(ingredientCommand);

        // then
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(ID_VALUE, convert.getId());
        Assertions.assertEquals(AMOUNT, convert.getAmount());
        Assertions.assertEquals(DESCRIPTION, convert.getDescription());
        Assertions.assertEquals(UOM_ID_VALUE, convert.getUom().getId());
    }
}