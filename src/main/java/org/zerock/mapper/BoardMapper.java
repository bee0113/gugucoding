package org.zerock.mapper;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    /*게시판 조회*/
    //@Select("SELECT * FROM tbl_board WHERE bno > 0")
    public List<BoardVO> getList();

    public List<BoardVO> getListWithPaging(Criteria cri);

    /*게시물 작성*/
    //insert만 처리되고 생성된 PK 값을 알 필요가 없는 경우
    public void insert(BoardVO board);

    //insert문이 실행되고 생성된 PK 값을 알아야 하는 경우
    public void insertSelectKey(BoardVO board);

    /*게시물 상세 조회*/
    public  BoardVO read(Long bno);

    /*게시물 삭제*/
    public int delete(Long bno);

    /*게시물 수정*/
    public int update(BoardVO board);


}
