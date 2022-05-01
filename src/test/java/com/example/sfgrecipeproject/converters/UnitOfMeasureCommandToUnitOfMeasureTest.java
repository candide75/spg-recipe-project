package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.UnitOfMeasureCommand;
import com.example.sfgrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID_VALUE);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        // when
        UnitOfMeasure unitOfMeasure = converter.convert(unitOfMeasureCommand);

        // then
        Assertions.assertNotNull(unitOfMeasure);
        Assertions.assertEquals(ID_VALUE, unitOfMeasure.getId());
        Assertions.assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}