//다양한 상황에 맞게 동적으로 JPQL을 생성하는 방법
package kr.ac.kopo.guestbook2024.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder //객체 생성
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{ //속성이 하나도 없으면 오류남
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동으로 추가
    private Long gno;

    @Column(length = 100, nullable = false) // column에 자료형 지정, null 값 지정하지 않음
    private String title;

    @Column(length = 1500, nullable = false) // column에 자료형 지정, null 값 지정하지 않음
    private String content;

    @Column(length = 100, nullable = false) // column에 자료형 지정, null 값 지정하지 않음
    private String writer;

    //업데이트
    public void changeTitle(String title){
        this.title = title;
    } // changeTitle
    public void changeContent(String content){
        this.content = content;
    } //changeContent



}
