 
package org.conan.controller;

import org.conan.domain.BoardVO;
import org.conan.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller

@Log4j

@RequestMapping("/board/*")

@AllArgsConstructor
public class BoardController {

	private BoardService service;

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}

	@PostMapping("/register") //게시글 저장
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		return "redirect:/board/list";
	}
	@GetMapping("/register")
	public void register() {
		//url 로 들어가는걸 postMapping이 허용하지 않아서 하나 만들어줌
	}

	@GetMapping("/get")
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get");
		model.addAttribute("board",service.get(bno));
	}
	
	@PostMapping("/modify")
	public String get(BoardVO board, RedirectAttributes rttr) {
		log.info("modify : "+board);
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		log.info("remove............."+bno);
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}

}
