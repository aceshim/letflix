package ppt.dao;

import static common.Util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ppt.entity.PptEntity;
import tag.entity.TagEntity;

public class PptDao {

	public PptEntity selectPpt(String pptId, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PptEntity ppt = null;

		try {
			String sql = "select ppt_id, user_id, ppt_date, ppt_location, ppt_length, ppt_like, ppt_views, ppt_title from tb_ppt where ppt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				ppt = new PptEntity(pptId, rs.getString(2), rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(5)),Integer.parseInt(rs.getString(6)), Integer.parseInt(rs.getString(7)), rs.getString(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return ppt;
	}

	public ArrayList<PptEntity> selectPptList(String userId, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		PreparedStatement subStmt = null;
		ResultSet subRs = null;
		ResultSet rs = null;
		ArrayList<PptEntity> pptList =  new ArrayList<PptEntity>();

		try {
			String sql = "select ppt_id, ppt_date, ppt_location, ppt_length, ppt_like, ppt_views, ppt_title from tb_ppt where user_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();

			while (rs.next()) {
				
				PptEntity ppt = new PptEntity(rs.getString(1), userId, rs.getString(2), rs.getString(3), Integer.parseInt(rs.getString(4)), Integer.parseInt(rs.getString(5)), Integer.parseInt(rs.getString(6)), rs.getString(7));
				ppt.setTagList(new ArrayList<TagEntity>());
				String subSql = "SELECT tag_id, tag_name FROM tb_tagup JOIN tb_tag USING(tag_id) WHERE ppt_id=?";
				subStmt = conn.prepareStatement(subSql);
				subStmt.setString(1, rs.getString(1));
				subRs = subStmt.executeQuery();
				while (subRs.next()) {
					ppt.getTagList().add(new TagEntity(subRs.getString(1), subRs.getString(2)));
				}
				
				pptList.add(ppt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(subRs);
			close(subStmt);
			close(rs);
			close(stmt);
		}
		return pptList;
	}

	public int insertPpt(PptEntity ppt, Connection conn) throws Exception  {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "insert into tb_ppt values(?,?,SYSDATE,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ppt.getPptId());
			stmt.setString(2, ppt.getUserId());
			stmt.setString(3, ppt.getPptLocation());
			stmt.setString(4, Integer.toString(ppt.getPptLength()));
			stmt.setString(5, Integer.toString(ppt.getPptLike()));
			stmt.setString(6, Integer.toString(ppt.getPptViews()));
			stmt.setString(7, ppt.getPptTitle());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}

	public int updatePpt(PptEntity ppt, Connection conn) throws Exception  {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "update tb_ppt set user_id = ?, ppt_date=?, ppt_location = ?, ppt_length = ?, ppt_like = ?, ppt_views = ?, ppt_title = ? where ppt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ppt.getPptDate());
			stmt.setString(2, ppt.getPptDate());
			stmt.setString(3, ppt.getPptLocation());
			stmt.setString(4, Integer.toString(ppt.getPptLength()));
			stmt.setString(5, Integer.toString(ppt.getPptLike()));
			stmt.setString(6, Integer.toString(ppt.getPptViews()));
			stmt.setString(7, ppt.getPptId());
			stmt.setString(8, ppt.getPptTitle());
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}

	public int deletePpt(String pptId, Connection conn) throws Exception  {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "delete from tb_ppt where ppt_id = ?";
			stmt = conn.prepareStatement(sql);
			System.out.println(pptId);
			stmt.setString(1, pptId);
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}



	public int getLike(String pptId, Connection conn) {

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int likes = 0;
		try {

			String sql = "select ppt_like from tb_ppt where ppt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				likes = Integer.parseInt(rs.getString(1));
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}


		return likes;
	}

	public int like(String pptId,String userId, int likes, Connection conn) {
		// TODO Auto-generated method stub


		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		int result = 0;
		likes++;

		try {
			String sql = " update tb_ppt set ppt_like = ? where ppt_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ""+likes);
			stmt.setString(2, pptId);


			result = stmt.executeUpdate();

			String sql2 = "insert into tb_likehistory values(?,?,sysdate)";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, userId);
			stmt2.setString(2,pptId);

			result = stmt2.executeUpdate();





		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(stmt2);
		}
		return result;
	}


	public int unlike(String pptId,String userId, int likes, Connection conn) {
		// TODO Auto-generated method stub


		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;

		int result = 0;
		likes--;

		try {
			String sql = " update tb_ppt set ppt_like = ? where ppt_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ""+likes);
			stmt.setString(2, pptId);


			result = stmt.executeUpdate();


			String sql2 = "delete from tb_likehistory where user_id = ? and  ppt_id= ? ";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setString(1, userId);
			stmt2.setString(2,pptId);

			result = stmt2.executeUpdate();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}

	public int likedBefore(String pptId, String userId, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int liked = 0;

		try {
			String sql = "select * from tb_likehistory where user_id= ? and ppt_id = ? ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);
			stmt.setString(2, pptId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				liked = 1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		return liked;
	}


	public int getViewCount(String pptId, Connection conn) {

		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int viewCount = 0;
		try {

			String sql = "select ppt_views from tb_ppt where ppt_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				viewCount = Integer.parseInt(rs.getString(1));
			}


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}


		return viewCount;
	}
	
	public int incViewCount(String pptId, int viewCount, Connection conn) {
		// TODO Auto-generated method stub


		PreparedStatement stmt = null;
		
		int result = 0;
		viewCount++;

		try {
			String sql = " update tb_ppt set ppt_views = ? where ppt_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, ""+viewCount);
			stmt.setString(2, pptId);


			result = stmt.executeUpdate();



		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			
		}
		return result;
	}

	public int deletePptTag(String pptId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "DELETE FROM tb_tagup WHERE ppt_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			
			result = stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(stmt);
		}
		return result;
	}

	public String getNextPptNum(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String nextPptNum = null;
		
		try {
			String sql =  "SELECT 'pp_'||LPAD(MAX(NVL(SUBSTR(ppt_id, 4), 0)) + 1, 5, 0) FROM tb_ppt";
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				nextPptNum = rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return nextPptNum;
	}

	public String getLastPptNum(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String nextPptNum = null;
		
		try {
			String sql =  "SELECT 'pp_'||LPAD(MAX(NVL(SUBSTR(ppt_id, 4), 1)), 5, 0) FROM tb_ppt";
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				nextPptNum = rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return nextPptNum;
	}


}
