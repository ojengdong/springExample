package kr.top2blue.JsonProject.vo;

import java.util.List;

import lombok.Data;

@Data
public class BoxOffice2 {
	
	private BoxOfficeResult boxOfficeResult;
	
	@Data
	public static class BoxOfficeResult{
		private String boxofficeType;
		private String showRange;
		private String yearWeekTime;
		private List<WeeklyBoxOfficeList> WeeklyBoxOfficeList;
	}
	
	@Data
	public static class WeeklyBoxOfficeList{
		private String rnum;
		private String rank;
		private String rankInten;
		private String rankOldAndNew;
		private String movieCd;
		private String movieNm;
		private String openDt;
		private String salesAmt;
		private String salesShare;
		private String salesInten;
		private String salesChange;
		private String salesAcc;
		private String audiCnt;
		private String audiInten;
		private String audiChange;
		private String audiAcc;
		private String scrnCnt;
		private String showCnt;
	}
}
