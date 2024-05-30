package kr.ac.kopo.guestbook2024.service;

import kr.ac.kopo.guestbook2024.dto.GuestbookDTO;
import kr.ac.kopo.guestbook2024.dto.PageRequestDTO;
import kr.ac.kopo.guestbook2024.dto.PageResultDTO;
import kr.ac.kopo.guestbook2024.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface GuestbookService {
// 글 등록 가능
    Long register(GuestbookDTO dto);
    //한 페이지에 보여질 글 목록(GuestbookDTO 객체) 이 저장된 list 정보를 갖고 있는 PageResultDTO객체 참조값을 반환
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);
    GuestbookDTO read(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO dto){

        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    } // default

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    } //default


}
