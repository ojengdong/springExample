package kr.kdata.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import kr.kdata.member.service.MakePassword;

@SpringBootTest
class BootMemberEx01ApplicationTests {

	@Test
	void contextLoads() {
		
		for(int i=0;i<20;i++) {
			System.out.println(MakePassword.makePassword(10));
		}
		
	}

}
