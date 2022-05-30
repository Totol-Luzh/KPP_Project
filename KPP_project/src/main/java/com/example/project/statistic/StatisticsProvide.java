package com.example.project.statistic;

import com.example.project.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Service
public class StatisticsProvide {

    private static List<Double> roots = new ArrayList<>();

    private Statistics stats;

    private static boolean shouldBeRecalculated = true;

    @Autowired
    public void setStats(Statistics stats) {
        this.stats = stats;
    }

    public Statistics getStats() {
        return stats;
    }

    public void increaseTotalRequests() {
        stats.totalRequests++;
    }

    public void increaseWrongRequests() {
        stats.wrongRequests++;
    }

    public void calculate() {

        ProgramLogger.log(Level.INFO, "Collecting stats...");

        if (!shouldBeRecalculated) {
            ProgramLogger.log(Level.WARN, "Stats need not to be recollected!");
            return;
        }

        try {
            stats.mostCommon = roots
                    .stream()
                    .reduce(
                            BinaryOperator.maxBy(Comparator.comparingInt(o -> Collections.frequency(roots, o)))
                    ).orElse(0.0);

            roots = roots
                    .stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            stats.min = roots
                    .stream()
                    .min(Comparator.comparing(Double::valueOf))
                    .orElse(0.0);

            stats.max = roots
                    .stream()
                    .max(Comparator.comparing(Double::valueOf))
                    .orElse(0.0);

            ProgramLogger.log(Level.WARN, "Stats recollected!");

            shouldBeRecalculated = false;
        } catch (NullPointerException exception) {
            ProgramLogger.log(Level.ERROR, "Error while collecting stats!");
            throw new RuntimeException(exception);
        }
    }

    public void addRoot(@NotNull Double root) {
        roots.add(root);
        shouldBeRecalculated = true;
    }
}
