package com.example.sfgrecipeproject.repositories;

import com.example.sfgrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIntegrationTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepository.findByDescription("Teaspoon");
        Assertions.assertEquals("Teaspoon", optionalUom.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> optionalUom = unitOfMeasureRepository.findByDescription("Cup");
        Assertions.assertEquals("Cup", optionalUom.get().getDescription());
    }
}