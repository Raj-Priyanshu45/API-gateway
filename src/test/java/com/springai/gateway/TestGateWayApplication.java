package com.springai.gateway;

import org.springframework.boot.SpringApplication;

public class TestGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.from(GateWayApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
