package kr.top2.blue.calendar.vo;

import lombok.Data;

@Data
public class LunarVO {
   private String solar;
   private String lunar;
   private String ganji;
   private String dayOfWeek;

   // 양력을 분해해서 년/월/일을 따로 얻어내는 메서드를 추가해보자
   // 1965년 12월 01일
   public int getSolarYear() { // 양력 년도만 얻기
      return Integer.parseInt(solar.substring(0, 4));
   }

   public int getSolarMonth() { // 양력 월만 얻기
      // split(" ") : 구분자를 기준으로 잘라서 배열로 만든다.
      return Integer.parseInt(solar.split(" ")[1].substring(0, 2));
   }

   public int getSolarDate() { // 양력 일만 얻기
      return Integer.parseInt(solar.split(" ")[2].substring(0, 2));
   }

   // 음력을 분해해서 년/월/일을 따로 얻어내는 메서드를 추가해보자
   // 2023년 윤02월 01일
   public int getLunarYear() { // 양력 년도만 얻기
      return Integer.parseInt(lunar.substring(0, 4));
   }

   public String getLunarMonth() { // 양력 월만 얻기
      // split(" ") : 구분자를 기준으로 잘라서 배열로 만든다.
      String m = lunar.split(" ")[1];
      return m.substring(0, m.length() - 1);
   }

   public int getLunarDate() { // 양력 일만 얻기
      return Integer.parseInt(lunar.split(" ")[2].substring(0, 2));
   }

   // 간지를 세차/월간/일진 으로 각각 구분하여 가져오기
   // 평달의 경우 : 개요년 음묘월 무연일
   // 윤달의 경우 : 계묘년 기묘일 ==> 윤달은 월건이 없다.
   // 년도를 얻어보자
   // 한글
   public String getGanjiYearK() {
      return ganji.substring(0, 2);
   }

   // 한자
   public String getGanjiYearH() {
      return ganji.substring(3, 5);
   }

   // 월간을 읽어보자
   public String getGanjiMonthK() {
      String[] m = ganji.split(" "); // 빈칸으로 구분해서 배열로 만든다.

      if (m.length == 3) { // 평달
         return m[1].substring(0, 2);
      } else { // 윤달
         return "";
      }
   }

   // 한자
   public String getGanjiMonthH() {
      String[] m = ganji.split(" ");
      if (m.length == 3) { // 평달
         return m[1].substring(3, 5);
      } else { // 윤달
         return "";
      }
   }

   // 일간을 읽어보자
   public String getGanjiDateK() {
      String[] m = ganji.split(" "); // 빈칸으로 구분해서 배열로 만든다.

      if (m.length == 3) { // 평달
         return m[2].substring(0, 2);
      } else { // 윤달
         return m[1].substring(0, 2);
      }
   }

   // 한자
   public String getGanjiDateH() {
      String[] m = ganji.split(" ");
      if (m.length == 3) { // 평달
         return m[2].substring(3, 5);
      } else { // 윤달
         return m[1].substring(3, 5);
      }
   }

   
   //요일을 문자로 얻는 메서드
   public String getDayOfWeekS() {
      return dayOfWeek.substring(0,2); //==> 일 월 화 ..토
      
   }
   
   //요일을 숫자로 얻는 메서드
   public int getDayOfWeekN() {
      return "일월화수목금토".indexOf(dayOfWeek.substring(0,1)); //0,1,2,3,4,5,6
   }
   
}