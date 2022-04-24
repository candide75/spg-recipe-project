package com.example.sfgrecipeproject.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndexControllerTest {

    IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
    }

    @Test
    void getIndexPage() {
        Assertions.assertEquals("index", indexController.getIndexPage());
    }
}