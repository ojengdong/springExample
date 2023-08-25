package kr.top2blue.board.vo;
//이거 만들어놓으면 @RequestParam 만들어서 세개 만들어야하는데 이 클래스를 만들음으로써, Model로 받을 수 있게 된다.
import lombok.Data;

@Data 
public class CommVO {
    private int p; //현재페이지
    private int s; //페이지당 글수 
    private int b; //하단 페이지 개수
}