package com.example.GroupBuying.service;

import com.example.GroupBuying.dto.BoardDTO;
import com.example.GroupBuying.entity.Board;
import com.example.GroupBuying.entity.MemberEntity;
import com.example.GroupBuying.repository.BoardRepository;
import com.example.GroupBuying.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final HttpSession session;
    public void write(BoardDTO boardDTO) {

        String loginId = (String) session.getAttribute("loginId");
        Optional<MemberEntity> byMember = memberRepository.findById(loginId);

        MemberEntity member=byMember.get();

        Board board = Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .item(boardDTO.getItem())
                .category(boardDTO.getCategory())
                .content(boardDTO.getContent())
                .recruitment(boardDTO.getRecruitment())
                .datetime(LocalDateTime.now())
                .date(LocalDate.now())
                .writer(loginId)
                .member(member)
                .build();
        boardRepository.save(board);
    }


    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> searchKeyList(String searchKey) {
        return boardRepository.findByTitleContaining(searchKey);
    }

    public List<Board> findByWriter(String loginId) {
        System.out.println("loginId = " + loginId);
        return boardRepository.findByWriter(loginId);
    }
}
