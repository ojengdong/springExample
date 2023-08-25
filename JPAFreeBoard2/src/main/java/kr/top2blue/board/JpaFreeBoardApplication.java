package kr.top2blue.board;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import kr.top2blue.board.dao.BoardRepository;
import kr.top2blue.board.vo.Board;

@SpringBootApplication
public class JpaFreeBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaFreeBoardApplication.class, args);
	}

	@SuppressWarnings("unused")
	@Autowired
	private BoardRepository boardRepository;
	
	@Bean // 애플리케이션 시작시 자동으로 작동시키고 싶은 코드를 여기에 넣는다.
	CommandLineRunner getCommandLineRunner() {
		return (args)->{

			boardRepository.deleteAll();
			// 새롭게 시작할떄 마다 데이터가 256개가 저장!!!
			for(int i=1;i<256;i++) {
				boardRepository.save(new Board("주인장"+ i, "12345",
						                       "제목이야 " + i, "내용이야 " + i, new Date()));
			}
			System.out.println(boardRepository.count() + "개있다!!!!");
			/*
			System.out.println("글번호 오름차순!!!");
			for(Board vo : boardRepository.findAll()) {
				System.out.println(vo);
			}
			System.out.println("-".repeat(80));
			System.out.println("글번호 내림차순!!!");
			for(Board vo : boardRepository.findAllByOrderByIdDesc()) {
				System.out.println(vo);
			}
			System.out.println("-".repeat(80));
			*/
		};
	}
}
