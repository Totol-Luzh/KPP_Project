package com.example.project.controller;


import com.example.project.cache.SolutionCache;
import com.example.project.calculations.NewLogic;
import com.example.project.calculations.Parametres;
import com.example.project.calculations.Solution;
import com.example.project.exceptions.BadRequestException;
import com.example.project.exceptions.ServerException;
import com.example.project.logger.ProgramLogger;
import com.example.project.statistic.StatisticsProvide;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IntegrationController {
    private final Solution solution = new Solution();
    SystemCounter systemCalls = new SystemCounter();

    static double f(double x) {
        return Math.sin(x);
    }

    private StatisticsProvide statisticsProvide = new StatisticsProvide();

    void ValidationCheck(double start, double end) {
        if (start < 0.0 && end < 0.0) {
            throw new BadRequestException("Wrong data! Parameter START and END");
        }
        if (start < 0.0) {
            throw new BadRequestException("Wrong data! Parameter START");
        }
        if (end < 0.0) {
            throw new BadRequestException("Wrong data! Parameter END");
        }
        if (start > end) {
            throw new BadRequestException("Impossible situation! START > END");
        }
    }

    @GetMapping(value = "/integration")
    public ResponseEntity<Object> calculate(@RequestParam(value = "start", defaultValue = "0") Double start,
                            @RequestParam(value = "end", defaultValue = "1") Double end) throws BadRequestException, ServerException {


        ValidationCheck(start, end);

        systemCalls.increasingCalls();

        solution.setValues(new Parametres(start, end));
        solution.calculateRoot();

        return new ResponseEntity<>(solution.getRoot(), HttpStatus.OK);
    }

    @PostMapping(value = "/post",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> sum(@RequestBody String[] arr) {
        ProgramLogger.log(Level.INFO,"input reached");
        NewLogic countInt = new NewLogic() ;
        return new ResponseEntity<>(countInt.countIntegration(arr), HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<String> printCache() {
        return new ResponseEntity<>(SolutionCache.getStaticStringCache(), HttpStatus.OK);
    }
}
