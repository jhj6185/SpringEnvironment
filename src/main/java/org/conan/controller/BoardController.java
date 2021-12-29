 
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

	private BoardService service; //serviceImpl로 가야함 service엔 걍 함수이름만 선언되어있고 기능은 Impl에 있음

	@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}

	@PostMapping("/register") //게시글 저장
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board); //board는 게시글을 저장할 때 딱 하나의 bno,title,content,writer을 갖잖아
		service.register(board);//register가 실행되면 register안에 있는 selectKey도 실행됨
		rttr.addFlashAttribute("result", board.getBno()); //getBno는 BoardVO에서 번호를 가져오는거고
		//selectKey는 mysql의 bno의 값을 가져와서 boardVO에 setting해주는것.
		//그니까 순서상 register의 selectKey가 boardVO의 bno는 첨에 0일텐데 그걸 mysql에서 가져와서 setting해주고
		//그 후에 그 번호를 가져와주는게 board.getBno()
		return "redirect:/board/list"; //redirect 하지 않으면 계속 confirm하라는 alert창이뜸 
		//그래서 redirect해주는게좋음
	}
	@GetMapping("/register")
	public void register() {
		//url 로 들어가는걸 postMapping이 허용하지 않아서 하나 만들어줌
	}

	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		log.info("/get or modify");
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
