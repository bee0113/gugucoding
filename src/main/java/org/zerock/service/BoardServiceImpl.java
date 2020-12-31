package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Log4j
@Service
// 모든 파라미터를 이용하는 생성자 셍성
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

//	@Setter(onMethod_ = @Autowired)
	//@AllArgsConstructor로 인해 spring 4.3 이상에서 자동 처리
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {

		log.info("register......" + board);

		mapper.insertSelectKey(board);
	}

	/*게시물 상세 조회*/
	@Override
	public BoardVO get(Long bno) {

		log.info("get......" + bno);

		return mapper.read(bno);

	}

	/*게시물 수정*/
	@Override
	public boolean modify(BoardVO board) {

		log.info("modify......" + board);

		return mapper.update(board) == 1;
	}

	/*게시물 삭제*/
	@Override
	public boolean remove(Long bno) {

		log.info("remove...." + bno);

		return mapper.delete(bno) == 1;
	}

	/*게시물 전체 조회*/
	 @Override
	 public List<BoardVO> getList() {

	 log.info("getList..........");

	 return mapper.getList();
	 }

//	@Override
//	public List<BoardVO> getList(Criteria cri) {
//
//		log.info("get List with criteria: " + cri);
//
//		return mapper.getListWithPaging(cri);
//	}
//
//	@Override
//	public int getTotal(Criteria cri) {
//
//		log.info("get total count");
//		return mapper.getTotalCount(cri);
//	}

}
