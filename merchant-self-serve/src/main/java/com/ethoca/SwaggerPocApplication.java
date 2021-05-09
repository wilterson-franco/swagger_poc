package com.ethoca;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SwaggerPocApplication implements CommandLineRunner {

    private MerchantSelfServeService mssService;

    public SwaggerPocApplication(MerchantSelfServeService mssService) {
        this.mssService = mssService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SwaggerPocApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mssService.doSomething();
    }
}
