package com.dongle.board.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;
import com.dongle.board.model.dao.BoardDao;
import com.dongle.board.model.vo.Board;
import com.dongle.board.model.vo.BoardComment;
import com.dongle.board.model.vo.BoardPath;

public class BoardService {
	
	public List<Board> selectList(int groupNo, String memberId)
	{
		Connection conn=getConnection();
		List<Board> list=new BoardDao().selectList(conn,groupNo,memberId);
		close(conn);
		return list;
	}
	public Board selectOne(int boardNo,int groupNo, boolean hasRead)
	{
		Connection conn=getConnection();
		Board b =new BoardDao().selectOne(conn,boardNo,groupNo);
		if(!hasRead)
		{
			int result=new BoardDao().updateCount(conn,boardNo);
			if(result>0)
			{
				commit(conn);
			}
			else
			{
				System.out.println("롤");
				rollback(conn);
			}
		}
		close(conn);
		return b;
	}
	
	public int insertBoard(BoardPath bp)
	{
		Connection conn=getConnection();
		int result=new BoardDao().insertBoard(conn,bp);
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
	
	public int insertBoardFile(BoardPath bp,Board bo)
	{
		Connection conn=getConnection();
		int rs=new BoardDao().insertBoardFile(conn,bp,bo);
		if(rs!=0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		return rs;
	}
	
	public BoardPath selectBoardPath(int boardNo,int groupNo)
	{
		Connection conn=getConnection();
		BoardPath bp=new BoardDao().selectBoardPath(conn, boardNo, groupNo);
		close(conn);
		return bp;
	}
	
	public int deleteBoard(int boardNo, int groupNo)
	{
		Connection conn=getConnection();
		int result =new BoardDao().deleteBoard(conn,boardNo, groupNo);
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
	
	public int updateBoardPath(BoardPath bp)
	{
		Connection conn=getConnection();
		int result=new BoardDao().updateBoardPath(conn, bp);
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
	
	public int updateBoard(Board b)
	{
		Connection conn=getConnection();
		int result=new BoardDao().updateBoard(conn, b);
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
	
	public int insertBoComment(BoardComment bc)
	{
		Connection conn=getConnection();
		int result=new BoardDao().insertBoComment(conn,bc);
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
	
	public List<BoardComment> selectCommentList(int boardNo,int groupNo)
	{
		Connection conn=getConnection();
		List<BoardComment> bclist=new BoardDao().selectBoCommentList(conn, boardNo, groupNo);
		close(conn);
		return bclist;
	}
	public int deleteBoComment (int boCommentNo)
	{
		Connection conn=getConnection();
		int result = new BoardDao().deleteBoComment(conn, boCommentNo);
		if (result > 0)
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
	
