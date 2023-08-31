package kr.kdata.poll.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.kdata.poll.vo.HanjaVO;

@Service
public class HanjaService {
	// JSON파일 전체 읽기
	public List<HanjaVO> getAll(){
		List<HanjaVO> list = null;
		ObjectMapper objectMapper = new ObjectMapper();
		File file = new File("src/main/resources/static/data/hanja2test.json");
		try {
			HanjaVO[] hanjas = objectMapper.readValue(file, HanjaVO[].class);
			list = Arrays.asList(hanjas);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 급수/회수만 중복없이 가져오기
	public List<String> getGrades(){
		Set<String> set = new TreeSet<>();
		for(HanjaVO vo : getAll()) {
			set.add(vo.getD()+" " + vo.getS());
		}
		return new ArrayList<>(set);
	}
	
	// 지정급수, 지정 횟수를 받아 해당 문제만 가져오기
	public List<HanjaVO> getList(String find){
		Set<HanjaVO> set = new TreeSet<>(); // 정렬을 지원하면서 중복을 제거하는 자료구조
		for(HanjaVO vo : getAll()) {
			if(find.equals(vo.getD()+" " + vo.getS())) {
				set.add(vo);
			}
		}
		return new ArrayList<>(set);
	}
}
