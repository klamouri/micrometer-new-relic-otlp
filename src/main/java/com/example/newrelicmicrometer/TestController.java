package com.example.newrelicmicrometer;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    private final DistributionSummary distributionSummary;
    private final Timer timer;

    TestController(MeterRegistry meterRegistry) {
        this.distributionSummary = DistributionSummary.builder("test.response.size")
                .description("Response size distribution summary")
                .baseUnit("bytes")
                .publishPercentiles(0.5, 0.75, 0.9, 0.95, 0.99)
                .register(meterRegistry);

        this.timer = Timer.builder("test.endpoint.timer")
                .description("Timer for test endpoint")
                .publishPercentiles(0.5, 0.75, 0.9, 0.95, 0.99)
                .register(meterRegistry);
    }


    @GetMapping("/test")
    public Map<String, String> test() {
        var randomExecutionTime = new Random().nextInt(3000,7000);
        var randomResponseSize = new Random().nextInt(0,2500);

        this.timer.record(randomExecutionTime, TimeUnit.MILLISECONDS);
        distributionSummary.record(randomResponseSize);
        return Map.of("message", "Hello World!",
                "randomExecutionTime", String.valueOf(randomExecutionTime),
                "randomNumber", String.valueOf(randomResponseSize));
    }
}
