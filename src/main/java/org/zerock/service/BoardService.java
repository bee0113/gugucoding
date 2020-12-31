package org.zerock.service;

import org.zerock.domain.BoardVO;

import java.util.List;

public interface BoardService {

	/*게시물 등록*/
	public void register(BoardVO board);

	/*게시물 상세 조회*/
	public BoardVO get(Long bno);

	/*게시물 수정*/
	public boolean modify(BoardVO board);

	/*게시물 삭제*/
	public boolean remove(Long bno);

	/*게시물 조회*/
	 public List<BoardVO> getList();

//	public List<BoardVO> getList(Criteria cri);

	//추가
//	public int getTotal(Criteria cri);

}
