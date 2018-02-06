package com.trading;

import com.trading.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.trading")
public class Application implements CommandLineRunner {

    @Autowired
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Run Application");
        //ReadFile.readFile();
        //System.out.println(demoService.demoRun(new SecondStratagy(), new BigDecimal(100), 2L, 0.6F));
    }


}
