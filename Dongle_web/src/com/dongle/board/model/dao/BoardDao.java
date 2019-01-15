package com.dongle.board.model.dao;

import static common.JDBCTemplate.close;
import com.dongle.board.model.vo.BoardPath;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dongle.board.model.vo.Board;
import com.dongle.board.model.vo.BoardComment;

public class BoardDao {
	private Properties prop=new Properties();
	
	public BoardDao() 
	{
		try
		{
			String file=BoardDao.class.getResource("./board_query.properties").getPath();
			prop.load(new FileReader(file));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	//게시판 글 불러오기
	public List<Board> selectList(Connection conn,int groupNo,String memberId)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		String sql=prop.getProperty("selectList");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				Board board=new Board();
				board.setBoardNo(rs.getInt("board_no"));
				board.setGroupNo(rs.getInt("group_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setBoardWriteDate(rs.getDate("board_write_date"));
				board.setBoardViewCount(rs.getInt("board_view_count"));
				list.add(board);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return list;
	}
	//게시판 글 보기
	public Board selectOne(Connection conn,int boardNo,int groupNo)
	{
		
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Board b=null;
		String sql=prop.getProperty("selectOne");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, groupNo);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				b=new Board();
				b.setBoardNo(rs.getInt("board_no"));
				b.setGroupNo(rs.getInt("group_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardWriteDate(rs.getDate("board_write_date"));
				b.setBoardViewCount(rs.getInt("board_view_count"));
				b.setBoardStatus(rs.getString("board_status"));
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(pstmt);
		}
		return b;
	}
	//게시판 글쓰기
	public int insertBoard(Connection conn, Board b,BoardPath bp,int groupNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,	b.getBoardNo());
			pstmt.setInt(2, b.getGroupNo());
			pstmt.setString(3, b.getBoardTitle());
			pstmt.setString(4, b.getBoardWriter());
			pstmt.setString(5,	b.getBoardContent());
			pstmt.setInt(6,	b.getBoardViewCount());
			pstmt.setString(7, bp.getBoardFileOldPath());
			pstmt.setString(8, bp.getBoardFileOldPath());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	//게시판 댓글쓰기
	public int insertComment(Connection conn, BoardComment bc)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertComment");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getBoCommentLevel());
			pstmt.setString(3, bc.getBoCommentContent());
			pstmt.setInt(4,bc.getBoarNo());
			pstmt.setString(5,bc.getBoCommentRef()==0?null:String.valueOf(bc.getBoCommentRef()));
			//pstmt.setInt(5,bc.getBoardCommentRef());
			result=pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return result;
		
	}
}
