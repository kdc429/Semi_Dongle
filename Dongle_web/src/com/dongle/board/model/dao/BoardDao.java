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
	public int insertBoard(Connection conn,BoardPath bp)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("insertBoard");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bp.getGroupNo());
			pstmt.setString(2, bp.getBoardTitle());
			pstmt.setString(3, bp.getBoardWriter());
			pstmt.setString(4,	bp.getBoardContent());
			pstmt.setInt(5,	0);
			result=pstmt.executeUpdate();			
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
	
	public int updateCount(Connection conn, int boardNo)
	{
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateCount");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
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
	
	public int insertBoardFile(Connection conn, BoardPath bp,Board bo)
	{
		PreparedStatement pstmt=null;
		int rs=0;
		String sql=prop.getProperty("insertBoardFile");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bo.getBoardNo());
			pstmt.setInt(2, bp.getGroupNo());
			pstmt.setString(3, bp.getBoardFileOldPath());
			pstmt.setString(4, bp.getBoardFileNewPath());
			rs=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return rs;
	}
	
	public BoardPath selectBoardPath(Connection conn,int boardNo,int groupNo)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardPath bp=null;
		String sql=prop.getProperty("selectBoardPath");
		System.out.println("DAO입니다: "+sql);
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNo);
			pstmt.setInt(2, boardNo);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				bp=new BoardPath();
				bp.setBoardNo(rs.getInt("board_no"));
				bp.setGroupNo(rs.getInt("group_no"));
				bp.setBoardTitle(rs.getString("board_title"));
				bp.setBoardContent(rs.getString("board_content"));
				bp.setBoardWriter(rs.getString("board_writer"));
				bp.setBoardWriteDate(rs.getDate("board_write_date"));
				bp.setBoardViewCount(rs.getInt("board_view_count"));
				bp.setBoardStatus(rs.getString("board_status"));
				bp.setBoardFileNo(rs.getInt("bo_file_no"));
				bp.setBoardFileOldPath(rs.getString("bo_file_old_path"));
				bp.setBoardFileNewPath(rs.getString("bo_file_new_path"));
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
		return bp;		
		}
	
	public int deleteBoard(Connection conn, int boardNo, int groupNo)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("deleteBoard");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, groupNo);
			result=pstmt.executeUpdate();
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
	
	public int updateBoardPath(Connection conn, BoardPath bp)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateBoardPath");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bp.getBoardFileOldPath());
			pstmt.setString(2, bp.getBoardFileNewPath());
			pstmt.setInt(3, bp.getBoardNo());
			pstmt.setInt(4, bp.getGroupNo());
			result=pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}
	
	public int updateBoard(Connection conn,Board b)
	{
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateBoard");
		try
		{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardNo());
			pstmt.setInt(4, b.getGroupNo());
			result = pstmt.executeUpdate();
		}
		catch(Exception e)
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
		public int insertBoComment(Connection conn, BoardComment bc)
		{
			System.out.println("코멘트: "+bc);
			PreparedStatement pstmt=null;
			int result=0;
			String sql=prop.getProperty("insertBoComment");
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, bc.getGroupNo());
				pstmt.setInt(2, bc.getBoardNo());
				pstmt.setInt(3,bc.getMemberNo());
				pstmt.setString(4,bc.getBoCommentContent());
				pstmt.setInt(5,bc.getBoCommentLevel());
				pstmt.setString(6,bc.getBoCommentRef()==0?null:String.valueOf(bc.getBoCommentRef()));
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
		
		public List<BoardComment> selectBoCommentList(Connection conn, int boardNo, int groupNo)
		{
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<BoardComment> list=new ArrayList();
			String sql=prop.getProperty("selectBoCommentList");
			try
			{
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, groupNo);
				pstmt.setInt(2, boardNo);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					BoardComment bc=new BoardComment();
					bc.setGroupNo(rs.getInt("group_no"));
					bc.setBoardNo(rs.getInt("board_no"));
					bc.setBoCommentNo(rs.getInt("bo_comment_no"));
					bc.setMemberNo(rs.getInt("member_no"));
					bc.setBoCommentContent(rs.getString("bo_comment_content"));
					bc.setBoCommentDate(rs.getDate("bo_comment_date"));
					bc.setBoCommentLevel(rs.getInt("bo_comment_level"));
					bc.setBoCommentRef(rs.getInt("bo_comment_ref"));
					bc.setGroupMemberNickname(rs.getString("group_member_nickname"));
					list.add(bc);
					System.out.println("들어오?? : "+bc);
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
}
