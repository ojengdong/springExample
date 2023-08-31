package kr.kdata.mybatis;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import kr.kdata.mybatis.dao.TestDAO;

import javax.sql.DataSource;

@SpringBootTest
class MybatisEx01ApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	void contextLoads() {
		assertNotNull(jdbcTemplate);
		assertNotNull(dataSource);
	}
	
//	마이바티스를 테스트 해 보자
	@Autowired
	TestDAO testDAO;
	
	@Test
	void mybatisTest() {
		System.out.println(testDAO.selectNowString());
		System.out.println(testDAO.selectNowDate());
	}

}
