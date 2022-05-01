package com.example.sfgrecipeproject.converters;

import com.example.sfgrecipeproject.commands.NotesCommand;
import com.example.sfgrecipeproject.domain.Notes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotesCommandToNotesTest {

    public static final Long ID_VALUE = 1L;
    public static final String RECIPE_NOTES = "recipe notes";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullParameter() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        Assertions.assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {
        // given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        // when
        Notes convert = converter.convert(notesCommand);

        // then
        Assertions.assertNotNull(convert);
        Assertions.assertEquals(ID_VALUE, convert.getId());
        Assertions.assertEquals(RECIPE_NOTES, convert.getRecipeNotes());
    }
}