package ppt.biz;

import static common.Util.JdbcUtil.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ppt.dao.PptDao;
import ppt.entity.PptEntity;

public class PptBiz {

	public PptEntity selectPpt(String pptId) throws SQLException {
		PptEntity ppt = new PptEntity();
		PptDao dao = new PptDao();
		Connection conn = null;
		try {
			conn = getConnection();
			ppt = dao.selectPpt(pptId, conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return ppt;
	}
	
	public ArrayList<PptEntity> selectPptList(String userId) throws SQLException {
		ArrayList<PptEntity> pptList = new ArrayList<PptEntity>();
		PptDao dao = new PptDao();
		Connection conn = null;
		try {
			conn = getConnection();
			pptList = dao.selectPptList(userId, conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return pptList;
	}
	
	public int insertPpt(PptEntity ppt) throws SQLException {
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertPpt(ppt, conn);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}
	
	public int updatePpt(PptEntity ppt) throws SQLException {
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.updatePpt(ppt, conn);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}
	
	public int deletePpt(String pptId) throws SQLException {
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deletePpt(pptId, conn);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}
	
	
	public int getLike(String pptId) throws SQLException{
		PptDao dao = new PptDao();
		Connection conn = null;
		int likes = 0;
		try {
			conn = getConnection();
			likes = dao.getLike(pptId, conn);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return likes;
	}
	
	public int like(String pptId,String userId, int likes) throws SQLException{
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.like(pptId,userId, likes, conn);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}
	
	public int unlike(String pptId,String userId, int likes) throws SQLException{
		PptDao dao = new PptDao();
		Connection conn = null;
		
		
		int result = 0;
		try {
			conn = getConnection();
			result = dao.unlike(pptId,userId, likes, conn);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}
	
	public int likedBefore(String pptId, String userId) throws SQLException{
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.likedBefore(pptId,userId, conn);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}
	
	public int getViewCount(String pptId) throws SQLException{
		PptDao dao = new PptDao();
		Connection conn = null;
		int viewCount = 0;
		try {
			conn = getConnection();
			viewCount = dao.getViewCount(pptId, conn);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return viewCount;
	}
	
	public int incViewCount(String pptId, int viewCount) throws SQLException{
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.incViewCount(pptId,viewCount, conn);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			e.printStackTrace();
		}finally {
			close(conn);
		}
		return result;
	}

	public int deletePptTag(String pptId) throws SQLException {
		// TODO Auto-generated method stub
		PptDao dao = new PptDao();
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = dao.deletePptTag(pptId, conn);
			conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			close(conn);
		}
		return result;
		
	}

	public String getNextPptNum() throws SQLException {
		Connection conn = null;
		PptDao dao = new PptDao();
		
		String nextPptNum = null;
		
		try {
			conn = getConnection();
			nextPptNum = dao.getNextPptNum(conn);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
		return nextPptNum;
	}
	
	public String getLastPptNum() throws SQLException {
		Connection conn = null;
		PptDao dao = new PptDao();
		
		String nextPptNum = null;
		
		try {
			conn = getConnection();
			nextPptNum = dao.getLastPptNum(conn);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
		return nextPptNum;
	}
	
	
	
}
