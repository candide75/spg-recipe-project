package com.example.sfgrecipeproject.services;

import java.util.Set;

import com.example.sfgrecipeproject.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
