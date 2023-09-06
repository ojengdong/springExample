package kr.kdata.member.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.member.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	private TestDAO testDAO;
	
	public Date selectToday() {
		return testDAO.selectToday();
	}
	
}
