package kr.green.springtest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.springtest.vo.BoardVo;

public interface BoardDao {

	public List<BoardVo> getBoards();

	public void setBoard(@Param("board")BoardVo board);

//	public BoardVo readBoard(@Param("id")Integer id);

	public BoardVo getBoard(@Param("id")Integer id);

//	public BoardVo delBoard(@Param("state")String state);

	public void updateBoard(@Param("board")BoardVo board);
}
