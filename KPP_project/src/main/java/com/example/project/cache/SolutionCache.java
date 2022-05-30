package com.example.project.cache;

import com.example.project.calculations.Parametres;
import com.example.project.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class SolutionCache {
    private static final Map<Parametres, Double> solutions = new HashMap<>();

    public void add(@NotNull Parametres params, @NotNull Double root) {
        if(!solutions.containsKey(params)) {
            solutions.put(params,root);
            ProgramLogger.log(Level.INFO, "Value " + params + "@" + root + " added to cache!");
        }
    }

    public @Nullable Double find(@NotNull Parametres params) {
        if(solutions.containsKey(params))
            return solutions.get(params);

        ProgramLogger.log(Level.WARN, "Value " + params + " was not found in cache!");
        return null;
    }

    public @NotNull String getStringCache() {
        return ("Cache:\n" + solutions);
    }

    public static @NotNull String getStaticStringCache() {
        return ("Cache:\n" + solutions);
    }

    public Map<Parametres, Double> getSolutions() {
        return solutions;
    }
}
