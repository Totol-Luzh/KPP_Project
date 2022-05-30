package com.example.project.controller;

import com.example.project.logger.ProgramLogger;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemCounter {
    static int calls = 0;

    synchronized public void increasingCalls() {
        calls++;
        ProgramLogger.log(Level.INFO, "Counter has been increased.");
    }

    synchronized public int getValue(){
        return calls;
    }

    @GetMapping("/serviceCalls")
    synchronized public int showCalls() {
        ProgramLogger.log(Level.INFO, "Calls mapping have been successfully done");
        return calls;
    }
}
