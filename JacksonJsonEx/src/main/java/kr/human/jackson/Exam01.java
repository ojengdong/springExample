package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.DataVO;

public class Exam01 {
   public static void main(String[] args) {
      ObjectMapper mapper = new ObjectMapper();
      
      try {
         DataVO dataVOS[] = mapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/data.json"), DataVO[].class);
         System.out.println("사자성어 개수: " + dataVOS.length + "개");
         
         for(DataVO vo : dataVOS) {
            System.out.print(vo.getH() + "(");
            System.out.print(vo.getK() + ") : ");
            System.out.println(vo.getT());
         }
         
         // 파일로 저장
         mapper.writeValue(new File("hanjaWord.json"), dataVOS);
         
      
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}