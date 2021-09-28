package io.wkrzywiec.blog.jvm.performance.app;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HeavyTaskScheduler {

    private final HeavyTaskService heavyTaskService;

    public HeavyTaskScheduler(HeavyTaskService heavyTaskService) {
        this.heavyTaskService = heavyTaskService;
    }

    @Scheduled(fixedRate = 15000, initialDelay = 5000)
    void scheduleCpuTaks() {
        heavyTaskService.cpuIntensiveTask();
    }

    @Scheduled(fixedRate = 15000, initialDelay = 7000)
    void scheduleMemoryIntensiveTaks() {
        heavyTaskService.memoryIntensiveTaks();
    }

    @Scheduled(fixedRate = 20000, initialDelay = 11000)
    void scheduleClassLoadingTaks() {
        heavyTaskService.fileIoIntensiveTask();
    }
}
