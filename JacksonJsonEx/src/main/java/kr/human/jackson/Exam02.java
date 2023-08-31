package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.MyDataVO2;

public class Exam02 {
   public static void main(String[] args) {
      ObjectMapper mapper = new ObjectMapper();
      
      try {
         MyDataVO2 myDataVO2 = mapper.readValue(new File("src/main/resources/Mydata2.json"), MyDataVO2.class);
         
         System.out.println(myDataVO2);
         
         System.out.println(mapper
        		 .writerWithDefaultPrettyPrinter()
        		 .writeValueAsString(myDataVO2)
        		 );
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

}