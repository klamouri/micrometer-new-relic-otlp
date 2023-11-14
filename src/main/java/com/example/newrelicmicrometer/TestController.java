package com.example.newrelicmicrometer;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TestController {

    private final DistributionSummary distributionSummary;

    TestController(MeterRegistry meterRegistry) {
        this.distributionSummary = DistributionSummary.builder("response.size")
                .description("Response size distribution summary")
                .baseUnit("bytes")
                .publishPercentiles(0.5, 0.75, 0.9, 0.95, 0.99)
                .register(meterRegistry);
    }


    @GetMapping("/test")
    public Void test() {
        var randomNumber = new Random().nextInt(0,2500);
        distributionSummary.record(randomNumber);
        return null;
    }
}
