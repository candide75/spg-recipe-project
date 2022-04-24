package com.example.sfgrecipeproject.repositories;

import com.example.sfgrecipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
