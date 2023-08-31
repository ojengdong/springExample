package kr.top2blue.vo;

public class DateUtil {
	// 윤년을 판단하는 메서드
	public static boolean isLeafYear(int year) {
		return year%400==0 || year%4==0 && year%100!=0;
	}
	
	// 월의 마지막을 구해주는 메서드
	public static int getLastday(int year, int month) {
		int m[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		m[1] = isLeafYear(year) ? 29 : 28;
		return m[month-1];
	}
	
	// 1년 1월 1일부터 전체일수를 구해주는 메서드
	public static int getTotalDays(int year, int month, int date) {
//		전년도까지의 일 수
		int totaldays = (year-1)*365 + (year-1)/4 - (year-1)/100 + (year-1)/400;
		
//		전월까지의 일 수
		for(int i=1;i<month;i++) totaldays += getLastday(year, i);
		
//		일수를 더 하면 전체 일 수가 된다 
		return totaldays+date;
	}
	
	// 요일을 구해주는 메서드
	// 총일수를 7로 나눈 나머지가 0이면 일요일 6이면 월요일이다.(성경책 참조)
	public static int getDayOfWeek(int year,int month, int date) {
		return getTotalDays(year, month, date)%7;
	}
	
//	public static void main(String[] args) {
//		System.out.println(isLeafYear(2023) ? 2023+"년은 윤년" : 2023+"년은 평년");
//		System.out.println(isLeafYear(2024) ? 2024+"년은 윤년" : 2024+"년은 평년");
//		System.out.println("2023년 2월 마자막날 : " + getLastday(2023, 2));
//		System.out.println("2024년 2월 마자막날 : " + getLastday(2024, 2));
//		
//		System.out.println("오늘까지의 총일수 : " + getTotalDays(2023, 8, 28));
//		System.out.println("오늘의 요일 : " + getDayOfWeek(2023, 8, 28));
//		String w[] = "일월화수목금토".split("");
//		for(int i=1;i<=getLastday(2023, 8);i++) {
//			System.out.println("2023년 8월 " + i + "일(" + 
//		                  getDayOfWeek(2023, 8, i) + ", "
//		                  + w[getDayOfWeek(2023, 8, i)] + ")" 
//		                  );
//		}
//	}
}
