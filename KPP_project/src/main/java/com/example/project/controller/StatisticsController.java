package com.example.project.controller;

import com.example.project.statistic.Statistics;
import com.example.project.statistic.StatisticsProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private final StatisticsProvide statsProvider;

    public StatisticsController(StatisticsProvide statsProvider) {
        this.statsProvider = statsProvider;
    }

    @GetMapping
    public ResponseEntity<Statistics> getAllStats() {
        statsProvider.calculate();
        return new ResponseEntity<>(statsProvider.getStats(), HttpStatus.OK);
    }
}
