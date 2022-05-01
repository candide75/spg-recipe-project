package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.NotesCommand;
import com.example.sfgrecipeproject.domain.Notes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotesToNotesCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "recipe notes";

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {
        // given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        // when
        NotesCommand convert = converter.convert(notes);

        // then
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(ID_VALUE, convert.getId());
        Assertions.assertEquals(RECIPE_NOTES, convert.getRecipeNotes());
    }
}