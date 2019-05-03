package cz.upce.votingsystemapplicationConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class VotingSystemApplicationConfigServer {

    public static void main(String[] args) {
        SpringApplication.run(VotingSystemApplicationConfigServer.class, args);
    }

}
