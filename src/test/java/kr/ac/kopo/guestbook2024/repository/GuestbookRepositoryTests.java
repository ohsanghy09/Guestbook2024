package kr.ac.kopo.guestbook2024.repository;


import kr.ac.kopo.guestbook2024.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired // 참조 변수를 통해서 데이터 자동 주입 //참조 변수 선언
    private GuestbookRepository guestbookRepository;

    @Test
    public void InsertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {

                Guestbook guestbook = Guestbook.builder()
                        .title("Title====" + i)
                        .content("Content====" + i)
                        .writer("Writer " + (i%10+1))
                        .build();
                guestbookRepository.save(guestbook);

        });
    } //InsertDummies

    //내용 수정
    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        if(result.isPresent()){
            Guestbook guestbook = result.get(); //객체 반환
            guestbook.changeTitle("Changed Title.....");
            guestbook.changeContent("Changed Content....");

            guestbookRepository.save(guestbook); //이 메서드로 업데이트, 삽입 모두 사용가능

        } // if

    }//updateTest


} //main}
