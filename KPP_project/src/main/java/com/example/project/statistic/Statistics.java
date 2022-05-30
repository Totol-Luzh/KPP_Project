package com.example.project.statistic;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Statistics {
    protected Long totalRequests = 0L;
    protected Long wrongRequests = 0L;

    protected Double min = 0.0;

    protected Double max = 0.0;

    protected Double mostCommon = 0.0;
}