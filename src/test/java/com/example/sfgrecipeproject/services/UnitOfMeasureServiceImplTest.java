package com.example.sfgrecipeproject.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.sfgrecipeproject.commands.UnitOfMeasureCommand;
import com.example.sfgrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.sfgrecipeproject.domain.UnitOfMeasure;
import com.example.sfgrecipeproject.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService unitOfMeasureService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void listAllUoms() throws Exception {
        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        Mockito.when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        // when
        Set<UnitOfMeasureCommand> uomcommands = unitOfMeasureService.listAllUoms();

        // then
        assertEquals(2, uomcommands.size());
        Mockito.verify(unitOfMeasureRepository, Mockito.times(1)).findAll();
    }

}