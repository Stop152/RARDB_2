package lv.accenture.bootcamp.rardb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan({"com.server", "com.server.config"})


@SpringBootApplication
public class RardbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RardbApplication.class, args);
	}

}
