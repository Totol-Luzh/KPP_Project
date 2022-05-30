package com.example.project.calculations;

import com.example.project.SpringConfig;
import com.example.project.cache.SolutionCache;
import com.example.project.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Solution {
    private final SolutionCache cache;

    private Parametres parametres;

    private Double root;

    public Solution(Parametres params) {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", SolutionCache.class);
        context.close();

        this.parametres = params;
    }

    public Solution() {
        var context = new AnnotationConfigApplicationContext(SpringConfig.class);
        cache = context.getBean("cache", SolutionCache.class);
        context.close();
    }
    interface Calc {
        double calculate(double n);
    }
    public void calculateRoot() {
        double precision = 10000;
        Calc calc = (n)->{
            var temp = cache.find(parametres);
            if (temp != null) {
                ProgramLogger.log(Level.WARN, "Value found in cache!");
                //setRoot(temp);
                n = temp;
                return n;
            }
            double h = (parametres.getEndValue() - parametres.getStartValue()) / precision;              // step size
            double sum = 0.5 * (f(parametres.getStartValue()) + f(parametres.getEndValue()));    // area
            for (int i = 1; i < precision; i++) {
                double x = parametres.getStartValue() + h * i;
                sum = sum + f(x);
            }
            n = sum * h;
            return n;
        };




        setRoot(calc.calculate(0));

        cache.add(parametres, root);
    }

    public void setValues(Parametres params) {
        this.parametres = params;
    }

    public Double getRoot() {
        return root;
    }

    static double f(double x) {
        return Math.sin(x);
    }

    public void  setRoot(@Nullable Double root) {
        this.root = root;
    }

}
