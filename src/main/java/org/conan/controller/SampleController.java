package org.conan.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.conan.domain.SampleDTO;
import org.conan.domain.SampleDTOList;
import org.conan.domain.TodoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*") // /sample/ 로 오는 모든 정보를 처리해주는 애
@Log4j
public class SampleController {
	@GetMapping("/ex01") // /sample/ex01 을 처리해주는애
	public String ex01(SampleDTO dto) {
		log.info(""+dto);
		return "ex01"; // view페이지 이름
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : "+name);
		log.info("age : "+age);
		return "ex02";
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : "+ids); // ids : [111,222,333]
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids : "+Arrays.toString(ids)); // array ids : [111,222,333]
		return "ex02List";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) { 
		//주소창에 /sample/ex02Bean?list%5B0%5D.name=conan&list%5B1%5D.name=rose
		// 로 치는데 %5B는 [, %5D는 ] 이다.
		// 주소창에 []를 직접치면 404ERROR가 떠서 이렇게 쓴다.
		log.info("list dtos : "+list);
		return "ex02Bean";
	}
	
	/* initBinder로 simpleDateFormat하는 방법도있는데 @DateTimeFormat(pattern="yyyy/MM/dd")도 있다
	 * @InitBinder public void initBinder(WebDataBinder binder) { SimpleDateFormat
	 * dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 * binder.registerCustomEditor(java.util.Date.class, new
	 * CustomDateEditor(dateFormat, false)); }
	 */
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : "+todo);
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) { 
		log.info("dto : "+dto);
		log.info("page : "+page);
		return "/sample/ex04";
	}
	
	@GetMapping("/ex05")
	public void ex05() {
		log.info("ex05..................");
	}
	
	@GetMapping("/ex06") //json으로 보내기
	public @ResponseBody SampleDTO ex06() {
		log.info("ex06..........................");
		SampleDTO dto=new SampleDTO();
		dto.setAge(10);
		dto.setName("conan");
		return dto;
	}
	
	@GetMapping("/ex07") //http 프로토콜의 헤더를 다루는 경우 : view를 안만들고 data가 넘어가는지 확인하기위한 용도
	public ResponseEntity <String> ex07(){//깊이있는 플젝을 위한...방법,, header에 객체로 data 실어보내기
		log.info("ex07.................");
		String msg=String.format("{\"name\":\"conan\"}");
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json; charset=UTF-8");
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload.......");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile>files) {
		for(MultipartFile file:files) {
			log.info("name : "+file.getOriginalFilename());
			log.info("size : "+file.getSize());
		}
	}
	
}
