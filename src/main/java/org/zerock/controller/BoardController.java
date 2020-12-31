package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
//모든 파라미터를 이용하는 생성자를 만듬
@AllArgsConstructor
public class BoardController {

    // @AllArgsConstructor 이용해서 생성자를 만들고 자동으로 주입(생성자를 통한 주입)
    private BoardService service;

    private final static String REDIRECT_URL = "redirect:/board/list";

    /*게시물 전체 조회*/
    @GetMapping("/list")
    public void list(Model model) {

        log.info("list");
        model.addAttribute("list", service.getList());

    }

//	 @GetMapping("/list")
//	 public void list(Criteria cri, Model model) {
//
//	 log.info("list: " + cri);
//	 model.addAttribute("list", service.getList(cri));
//
//	 }

//	@GetMapping("/list")
//	public void list(Criteria cri, Model model) {
//
//		log.info("list: " + cri);
//		model.addAttribute("list", service.getList(cri));
//		// model.addAttribute("pageMaker", new PageDTO(cri, 123));
//
//		int total = service.getTotal(cri);
//
//		log.info("total: " + total);
//
//		model.addAttribute("pageMaker", new PageDTO(cri, total));
//
//	}

    /*게시물 작성*/
    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {

        log.info("register: " + board);

        service.register(board);

        rttr.addFlashAttribute("result", board.getBno());

//        return "redirect:/board/list";
        return REDIRECT_URL;
    }

    /*게시물 상세 조회*/
    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, Model model) {

        log.info("/get or modify ");
        model.addAttribute("board", service.get(bno));
    }

//    @GetMapping({"/get", "/modify"})
//    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
//
//        log.info("/get or modify");
//        model.addAttribute("board", service.get(bno));
//    }

    /*게시물 수정*/
    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        log.info("modify:" + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

//    @PostMapping("/modify")
//    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
//        log.info("modify:" + board);
//
//        if (service.modify(board)) {
//            rttr.addFlashAttribute("result", "success");
//        }
//
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());
//
//        return REDIRECT_URL;
//    }

    /*게시물 삭제*/
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {

        log.info("remove..." + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

//    @PostMapping("/remove")
//    public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {
//
//        log.info("remove..." + bno);
//        if (service.remove(bno)) {
//            rttr.addFlashAttribute("result", "success");
//        }
//        rttr.addAttribute("pageNum", cri.getPageNum());
//        rttr.addAttribute("amount", cri.getAmount());
//        rttr.addAttribute("type", cri.getType());
//        rttr.addAttribute("keyword", cri.getKeyword());
//
//        return REDIRECT_URL;
//    }

}
