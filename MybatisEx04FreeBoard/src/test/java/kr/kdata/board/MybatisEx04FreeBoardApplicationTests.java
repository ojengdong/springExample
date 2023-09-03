package kr.kdata.board;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisEx04FreeBoardApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void stringSpeendTest() {
		// String 객체는 불변객체이다. 한번 만들어 지면 절대로 바뀌지 않는 객체다.
		// 그래서 연산할때마다 새로운 객체를 만들어서 리턴한다. 그래서 변수로 꼭 받아야 한다,
		// 연산시 속도가 현저하게 늘어난다.
		long startTime = System.currentTimeMillis(); // 시작 시간
		String str = "";
		for(int i=0;i<=100000;i++) str += "꽝!";
		long endTime = System.currentTimeMillis(); // 종료시간
		System.out.println("String 실행 시간 : " + (endTime-startTime) + "ms");
	}
	/* StringBuilder나 StringBuffer는 가변객체이다. 
	 * 연산시 새로운 객체를 만들지 않고 자신이 변한다. 연산 속도가 String 보다 엄청 빠르다.
	 * 문자열 연산시는 String을 사용하지 말고 빌더나 버퍼를 권장한다.
	 * StringBuffer는 예전 클래스로 멀티스레드에도 안전하다.
	 * StringBuilder는 새롭게 추가된 클래스로 싱글스레드에만 안전하게 작동한다.
	 */
	@Test
	void stringBuilderSpeendTest() {
		long startTime = System.currentTimeMillis(); // 시작 시간
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<=100000;i++) sb.append("꽝!");
		long endTime = System.currentTimeMillis(); // 종료시간
		System.out.println("StringBuilder 실행 시간 : " + (endTime-startTime) + "ms");
	}

	@Test
	void stringBufferSpeendTest() {
		long startTime = System.currentTimeMillis(); // 시작 시간
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<=100000;i++) sb.append("꽝!");
		long endTime = System.currentTimeMillis(); // 종료시간
		System.out.println("StringBuffer 실행 시간 : " + (endTime-startTime) + "ms");
	}
}
