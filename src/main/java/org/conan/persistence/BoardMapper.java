package org.conan.persistence;

import java.util.List;

import org.conan.domain.BoardVO;
import org.conan.domain.Criteria;

public interface BoardMapper {
	/* @Select("select * from tbl_board where bno>0") */
	public List<BoardVO> getListWithPaging(Criteria cri);
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	public BoardVO read(long l);
	public int delete(long pk);
	public int update(BoardVO board);
	public void insertSelectKey(BoardVO board);

	
}
