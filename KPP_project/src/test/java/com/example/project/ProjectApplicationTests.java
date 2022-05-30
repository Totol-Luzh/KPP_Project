package com.example.project;


import com.example.project.calculations.Parametres;
import com.example.project.calculations.Solution;
import com.example.project.controller.IntegrationController;
import com.example.project.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ProjectApplicationTests {

    private final IntegrationController integrationControllerTest = new IntegrationController();

    @Test
    void testCalculateResult() throws BadRequestException {
        var solution = new Solution(new Parametres(0.0, 1.0));
        solution.calculateRoot();
        double expected = 0.45969769374878006;
        assertEquals(expected, solution.getRoot());
    }
}
