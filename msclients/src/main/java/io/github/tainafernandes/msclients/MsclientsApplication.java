package io.github.tainafernandes.msclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class MsclientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsclientsApplication.class, args);
	}

}
