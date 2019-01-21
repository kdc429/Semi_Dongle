package com.dongle.board.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;
import com.dongle.board.model.dao.BoardDao;
import com.dongle.board.model.vo.Board;

public class BoardService {
	
	public List<Board> selectList(int groupNo, String memberId)
	{
		Connection conn=getConnection();
		List<Board> list=new BoardDao().selectList(conn,groupNo,memberId);
		close(conn);
		return list;
	}
	public Board selectOne(int boardNo,int groupNo)
	{
		Connection conn=getConnection();
		Board b =new BoardDao().selectOne(conn,boardNo,groupNo);
		close(conn);
		return b;
	}
	
	public int insertBoard(Board b,int boardNo, int groupNo)
	{
		Connection conn=getConnection();
		int result=new BoardDao().insertBoard(conn,b,boardNo,groupNo);
		if(result>0)
		{
			commit(conn);
		}
		else
		{
			rollback(conn);
		}
		close(conn);
		return result;
	}
}
