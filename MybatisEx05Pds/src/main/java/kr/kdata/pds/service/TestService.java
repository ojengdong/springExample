package kr.kdata.pds.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.pds.dao.TestDAO;

@Service
public class TestService {

	@Autowired
	private TestDAO testDAO;
	
	public Date getToday() {
		return testDAO.selectToday();
	}
}
