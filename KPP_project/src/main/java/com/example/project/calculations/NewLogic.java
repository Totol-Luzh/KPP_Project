package com.example.project.calculations;
import com.example.project.logger.ProgramLogger;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.stream.Stream;

public class NewLogic {
    public double countIntegration(String[] arr){
        double start = Double.parseDouble(arr[0]);
        double end = Double.parseDouble(arr[1]);
        double precision = 10000;
        double h = (end - start)/precision;
        double sum = 0.5 * (f(start+end));
        for(int i = 1; i<precision; i++){
            double x = start + h * i;
            sum = sum + f(x);
        }
        return sum * h;

    }
    static double f(double x) {
        return Math.sin(x);
    }

}
