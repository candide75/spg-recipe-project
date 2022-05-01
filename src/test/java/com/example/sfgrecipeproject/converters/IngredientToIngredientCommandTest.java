package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.IngredientCommand;
import com.example.sfgrecipeproject.domain.Ingredient;
import com.example.sfgrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "description";
    public static final Long UOM_ID_VALUE = 2L;

    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void convert() {
        // given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UOM_ID_VALUE);
        ingredient.setUom(unitOfMeasure);

        // when
        IngredientCommand convert = converter.convert(ingredient);

        // then
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(ID_VALUE, convert.getId());
        Assertions.assertEquals(AMOUNT, convert.getAmount());
        Assertions.assertEquals(DESCRIPTION, convert.getDescription());
        Assertions.assertEquals(UOM_ID_VALUE, convert.getUom().getId());
    }
}