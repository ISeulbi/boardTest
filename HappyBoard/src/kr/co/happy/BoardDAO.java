package kr.co.happy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	private final int PAGE_NO = 5;
	public static BoardDAO instance = null;
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private BoardDAO() {};
	
	public ArrayList<BoardDTO> getBoardList(int page, int btype){
		
		ArrayList <BoardDTO> result = new ArrayList<BoardDTO>();
		
		int page1 = (page-1)*PAGE_NO + 1;
		int page2 = (page)*PAGE_NO;

		try {
			conn = DBConnetor.getConn();
			String query = " select * from " + 
					" (select h.*, row_number() over(order by seq desc ) as rnum " + 
					" from h_board h where h.btype = ? ) " + 
					" where rnum between ? and ? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, page1);
			ps.setInt(3, page2);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBregdate(rs.getString("bregdate"));
				result.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnetor.close(conn, ps, rs);
		}
		return result;
	}
	
	public BoardDTO getBoardDetail(int bid) {
		BoardDTO result = new BoardDTO();
		try {
			conn = DBConnetor.getConn();
			String query = String.format(" select bid, btype, seq, btitle, bcontent, bregdate, pw from H_BOARD "
					+ " where bid= %d ", bid);
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result.setBid(rs.getInt("bid"));
				result.setBtype(rs.getInt("btype"));
				result.setSeq(rs.getInt("seq"));
				result.setBtitle(rs.getString("btitle"));
				result.setBcontent(rs.getString("bcontent"));
				result.setBregdate(rs.getString("bregdate"));
				result.setPw(rs.getString("pw"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnetor.close(conn, ps, rs);
		}
		return result;
	}
	
	public void setBoardInsert(int btype, String btitle, String bcontent, String pw) {
		try {
			conn = DBConnetor.getConn();
			String query = " insert into h_board " + 
					" ( bid, btype, seq, btitle, bcontent, pw) " + 
					" values " + 
					" ( (select nvl(max(bid),0)+1 from h_board) , ?, (select nvl(max(seq), 0)+1 from h_board where btype=? ), ?, ?, ?) ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, btype);
			ps.setString(3, btitle);
			ps.setString(4, bcontent);
			ps.setString(5, pw);
			ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnetor.close(conn, ps, rs);
		}
	}
	
	public int getPageNo (int btype) {
		int result = 0;
		int no = 0;
		try {
			conn = DBConnetor.getConn();
			String query = " select max(seq) as max from h_board where btype=? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getInt("max");
			}
			
			no = (result/(PAGE_NO+1)) +1;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBConnetor.close(conn, ps, rs);
		}
		return no;
	}
	
	public void setDelete(int bid) {
		try {
			conn = DBConnetor.getConn();
			String query = " DELETE FROM h_board WHERE bid=? ";
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);
			ps.executeQuery();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBConnetor.close(conn, ps, rs);
			}
	}
	
	public void setBoardUpdata(int bid, String btitle, String bcontent, String pw) {
		try {
			conn = DBConnetor.getConn();
			String query = " UPDATE h_board " + 
					"  SET BTITLE = ? , BCONTENT=? , PW=? where bid = ? ";
			ps = conn.prepareStatement(query);
			ps.setString(1, btitle);
			ps.setString(2, bcontent);
			ps.setString(3, pw);
			ps.setInt(4, bid);
			ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnetor.close(conn, ps, rs);
		}
	}
	
}
