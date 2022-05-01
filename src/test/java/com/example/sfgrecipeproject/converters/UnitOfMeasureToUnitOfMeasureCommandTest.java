package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.UnitOfMeasureCommand;
import com.example.sfgrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(ID_VALUE);
        unitOfMeasure.setDescription(DESCRIPTION);

        // when
        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        // then
        Assertions.assertNotNull(unitOfMeasureCommand);
        Assertions.assertEquals(ID_VALUE, unitOfMeasureCommand.getId());
        Assertions.assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}