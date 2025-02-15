package io.spring2go.authcodeserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.spring2go.authcodeserver.**.mapper")
public class AuthCodeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthCodeServerApplication.class, args);
	}
}
