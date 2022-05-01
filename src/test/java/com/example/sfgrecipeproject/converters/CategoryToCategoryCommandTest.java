package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.CategoryCommand;
import com.example.sfgrecipeproject.domain.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryToCategoryCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";

    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        // given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        // when
        CategoryCommand convert = converter.convert(category);

        // then
        Assertions.assertEquals(ID_VALUE, convert.getId());
        Assertions.assertEquals(DESCRIPTION, convert.getDescription());
    }
}