package kr.kdata.poll.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class PollVO {
   private int id;
   private String subject;
   private String item; // html|css|java|spring|js //이런식으로 "|"가 붙어 구분되어있음
   private String cnt;

   // 필요한 메서드 추가
   public List<String> getItems() {
      return Arrays.asList(item.split("\\|"));

   }

   // 투표수를 구분자로 구분하여 리스트로 리턴하는 메서드
   public List<Integer> getCounts() {
      List<Integer> list = new ArrayList<>();
      for (String s : cnt.split("\\|")) { // 구분자로 구분한 배열을
         list.add(Integer.parseInt(s)); // 정수로 바꿔서
      }
      return list;

   }

   // 리스트에 담겨진 내용을 다시 문자열로 결합하는 메서드
   public void setItems(List<String> item) {
      this.item = String.join("|", item);
   }

   public void setCounts(List<Integer> counts) {
      String str = "";
      for (int i = 0; i < counts.size(); i++) {
         str += counts.get(i);
         // 맨 마지막에는 |를 붙이지 않는다.
         if (i != counts.size() - 1)
            str += "|";
      }

      this.cnt = str;
   }

   // 전체 투표수를 구해주는 메서드
   public int getTotalCounts() {
      int total = 0;
      for (int i : getCounts())
         total += i;
      return total;
   }
}