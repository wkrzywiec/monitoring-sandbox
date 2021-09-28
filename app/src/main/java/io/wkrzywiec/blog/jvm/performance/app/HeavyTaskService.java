package io.wkrzywiec.blog.jvm.performance.app;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Scanner;

@Component
class HeavyTaskService {

    void cpuIntensiveTask() {
        log("Starting CPU intensive task...");
        int result = 0;
        long end = System.currentTimeMillis() + 3000;
        while ((end - System.currentTimeMillis()) > 0) {
            result += 13;
        }
        log("CPU intensive task has been finished. Result: " + result);
    }

    void memoryIntensiveTaks() {
        log("Starting memory heavy task...");
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++){
            sum += i;
        }
        log("Memory heavy task has been finished. Result: " + sum);
    }

    void fileIoIntensiveTask() {
        log("Starting I/O heavy task...");
        String result = "";
        try {
            InputStream in = new ClassPathResource("ev.json").getInputStream();
            Scanner scanner = new Scanner(in);
            StringBuilder sb = new StringBuilder();

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine() + System.lineSeparator());
            }
            result = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log("I/O heavy task has been finished. Result: " + result.hashCode());
    }

    private void log(String message) {
        Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(startTimestamp + "\t" + message);
    }
}
