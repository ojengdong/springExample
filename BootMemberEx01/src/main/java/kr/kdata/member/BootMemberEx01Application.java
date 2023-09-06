package kr.kdata.member;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootMemberEx01Application {

	public static void main(String[] args) {
		SpringApplication.run(BootMemberEx01Application.class, args);
	}
	// 애플리케이션 시작시 자동으로 시작되는 작업을 등록한다.
	@Bean
	CommandLineRunner getCommandLineRunner() {
		return (args)->{
			System.out.println("-".repeat(80));
			System.out.println("공식문서: https://springdoc.org/v2/");
			System.out.println("http://localhost:8080 으로 접속해서 확인하세요!!!");
			System.out.println("http://localhost:8080/swagger-ui/index.html 으로 접속해서 확인하세요!!!");
			System.out.println("-".repeat(80));
		};
	}
}
