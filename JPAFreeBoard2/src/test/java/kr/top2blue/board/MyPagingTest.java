package kr.top2blue.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import kr.top2blue.board.dao.BoardPagingRepository;
import kr.top2blue.board.vo.Board;

@SpringBootTest
public class MyPagingTest {
   
   @Autowired
   BoardPagingRepository repository;
   
   @Test
   public void pagingTest1() {
      int pageNo = 5;
        int pageSize = 20;

        // create pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // findAll method and pass pageable instance
        Page<Board> page = repository.findAll(pageable);

        List<Board> boards = page.getContent();

        boards.forEach((p) ->{
            System.out.println(p);
        });

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long totalElements = page.getTotalElements();
        // number of elements
        int numberOfElements = page.getNumberOfElements();
        // size
        int size = page.getSize();

        // last
        boolean isLast = page.isLast();
        // first
        boolean isFirst = page.isFirst();
        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println(" size ->" + size);
        System.out.println(" isLast -> " + isLast);
        System.out.println(" isFirst -> " + isFirst);
       }

}