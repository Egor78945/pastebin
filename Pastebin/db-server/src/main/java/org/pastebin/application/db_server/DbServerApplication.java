package org.pastebin.application.db_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class DbServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbServerApplication.class, args);
    }

}
