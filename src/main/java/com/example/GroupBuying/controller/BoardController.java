package com.example.GroupBuying.controller;

import com.example.GroupBuying.dto.BoardDTO;
import com.example.GroupBuying.entity.Board;
import com.example.GroupBuying.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/gesizak")
    public String writeForm(Model model, HttpSession session) {
        if(session.getAttribute("loginId")==null){
            model.addAttribute("message", "로그인 후 사용이 가능합니다.");
            model.addAttribute("searchUrl", "/");
            return "message";
        }
        return "gesi_zak";
    }

    @PostMapping("/gesizak")
    public String write(BoardDTO boardDTO, Model model) {
        boardService.write(boardDTO);
        List<Board> boardList =boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "gesi";
    }

    @GetMapping("/gesi")
    public String postForm(Model model, String searchKey){
        List<Board> boardList = null;
        if(searchKey==null) {
            boardList = boardService.findAll();
        } else {
            boardList = boardService.searchKeyList(searchKey);
        }
        model.addAttribute("boardList", boardList);
        return "gesi";
    }

    @GetMapping("/gongi")
    public String noticeForm(){
        return "notice";
    }

    @GetMapping("/jjim")
    public String loveForm(Model model, HttpSession session){
        List<Board> myboardList = null;
        String loginId = (String) session.getAttribute("loginId"); // 세션에서 아이디를 가져옴

        myboardList = boardService.findByWriter(loginId); // 로그인 아이디로 작성자가 일치하는 게시물을 가져옴
        model.addAttribute("boardList",myboardList);
        return "love";
    }

}
