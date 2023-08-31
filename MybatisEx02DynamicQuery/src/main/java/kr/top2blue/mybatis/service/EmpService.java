package kr.top2blue.mybatis.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.top2blue.mybatis.dao.EmpDAO;
import kr.top2blue.mybatis.vo.EmpVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpService {

	@Autowired
	private EmpDAO empDAO;
	
	public List<EmpVO> selectAll(Integer id, String job){
		log.info("selectAll  호출!!!");
		List<EmpVO> list = null;
		//---------------------------------------------------------
		try {
			// 여기서 로직을 수행한다.
			HashMap<String, String> map = new HashMap<>();
			map.put("id", id==null? "": id+"");
			map.put("job", job);
			list = empDAO.selectAll(map);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		//---------------------------------------------------------
		log.info("selectAll  린턴 : {}", list);
		return list;
	}
	
	public List<Integer> selectDeptNo(){
		log.info("selectDeptNo  호출!!!");
		List<Integer> list = null;
		//---------------------------------------------------------
		try {
			// 여기서 로직을 수행한다.
			list = empDAO.selectDeptNo();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		//---------------------------------------------------------
		log.info("selectDeptNo  린턴 : {}", list);
		return list;
	}
	
	public List<String> selectJobs(){
		log.info("selectJobs  호출!!!");
		List<String> list = null;
		//---------------------------------------------------------
		try {
			// 여기서 로직을 수행한다.
			list = empDAO.selectJobs();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		//---------------------------------------------------------
		log.info("selectJobs  린턴 : {}", list);
		return list;
	}
	
}
