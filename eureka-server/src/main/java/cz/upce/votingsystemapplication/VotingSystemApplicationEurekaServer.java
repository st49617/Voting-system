package cz.upce.votingsystemapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class VotingSystemApplicationEurekaServer {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplicationEurekaServer.class, args);
    }

}
