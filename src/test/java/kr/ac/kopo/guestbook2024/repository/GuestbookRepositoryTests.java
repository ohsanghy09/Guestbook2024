package kr.ac.kopo.guestbook2024.repository;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.ac.kopo.guestbook2024.entity.Guestbook;
import kr.ac.kopo.guestbook2024.entity.QGuestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    public void testQuery1(){ //단일항목 검색
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exp1 = qGuestbook.title.contains(keyword);
        builder.and(exp1);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook ->
        {
            System.out.println(guestbook);
        });
    } // testQuery1

    @Test
    public void testQuery2(){ //다중항목 검색
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        //여러 가지 조건
        BooleanExpression exp1 = qGuestbook.title.contains(keyword);
        BooleanExpression exp2 = qGuestbook.content.contains(keyword);

        BooleanExpression exp = exp1.or(exp2); // 여러 조건을 하나로 합쳐 참조변수 선언

        builder.and(exp);
        builder.and(qGuestbook.gno.gt(100L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
        result.stream().forEach(guestbook ->
        {
            System.out.println(guestbook);
        });
    } // testQuery2

} //main}
