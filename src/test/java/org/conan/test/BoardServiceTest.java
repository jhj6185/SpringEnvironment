package org.conan.test;

import static org.junit.Assert.assertNotNull;

import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;
import org.conan.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

 @ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

/*
 * @ContextConfiguration(
 * "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
 */
//root-context.xml을 읽겟다는뜻 그니까 xml파일에 context:scan 어쩌고로 service가 있어야함
//근데 mybatis도 여기서 읽으니까 아주아주 잘봐야함....ㅠㅠ
@Log4j
public class BoardServiceTest {
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service); //boardService가 null인지 아닌지 확인하고 실행하겠다
	}
	@Test
	public void testGetList() {
		/* service.getList().forEach(board->log.info(board)); */
		service.getList(new Criteria(2,5)).forEach(board->log.info(board));
	}
	//getList 해결하고 하기
	@Test
	public void testRegister() {
		BoardVO board=new BoardVO();
		board.setTitle("새글 새글 새글 from Service");
		board.setContent("새 내용 새 내용 from Service");
		board.setWriter("newbie");
		service.register(board);
		log.info("생성된 게시물의 번호 : "+board.getBno());
	}
	@Test
	public void testGet() {
		log.info(service.get(2L).getTitle());
	}
	@Test
	public void testDelete() {
		log.info("REMOVE RESULT : "+service.remove(3L));
	}
	@Test
	public void testUpdate() {
		BoardVO board=service.get(2L);
		if(board==null) { return; }
		board.setTitle("제목 수정 from Service");
		log.info("MODIFY RESULT : "+service.modify(board));
	}
}
