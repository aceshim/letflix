package user.dao;

import static common.Util.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import group.entity.GroupEntity;
import tag.entity.TagEntity;
import user.entity.UserEntity;

public class UserDao {
	public UserEntity login(String id, String pwd, Connection conn) {
		UserEntity user = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select USER_ID "   + 
					",USER_PW      " + 
					",USER_NAME     " + 
					",USER_TEAM     " + 
					",USER_POSITION " + 
					",USER_COMPANY  " + 
					",USER_PHONE    " + 
					",USER_PIC      " + 
					",USER_LATESTLOGINDATE      " + 
					" from TB_USER where USER_ID = ? AND USER_PW = ? ";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				user = new UserEntity();
				user.setUserId(id);
				user.setUserPwd(pwd);
				user.setUserName(rs.getString(3));
				user.setUserTeam(rs.getString(4));
				user.setUserPosition(rs.getString(5));
				user.setUserCompany(rs.getString(6));
				user.setUserPhone(rs.getString(7));
				user.setUserPic(rs.getString(8));
				user.setLastestLoginDate(rs.getString(9));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(rs);
			close(stmt);
		}
		return user;
	}
	
	
	public UserEntity getUserProfile(String id,Connection conn) {
		UserEntity user = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select USER_ID " + 
					",USER_NAME " + 
					",USER_TEAM " + 
					",USER_POSITION " + 
					",USER_COMPANY " + 
					",USER_PHONE " + 
					",USER_PIC " +
					",USER_LATESTLOGINDATE" +
					" from TB_USER where USER_ID = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);

			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				user = new UserEntity();
				user.setUserId(id);
				user.setUserName(rs.getString(2));
				user.setUserTeam(rs.getString(3));
				user.setUserPosition(rs.getString(4));
				user.setUserCompany(rs.getString(5));
				user.setUserPhone(rs.getString(6));
				user.setUserPic(rs.getString(7));
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(rs);
			close(stmt);
		}
		return user;
	}
	
	
	public int getUserGroupCount(String id,Connection conn) {
		int userGroupCount = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="select count(*) from tb_groupuser where user_id = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);

			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				userGroupCount = Integer.parseInt(rs.getString(1));
				
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(rs);
			close(stmt);
		}
		return userGroupCount;
	}
	
	
	public int getUserPostCount(String id,Connection conn) {
		int userPostCount = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql ="select count(*) from tb_post where user_id = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);

			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				userPostCount = Integer.parseInt(rs.getString(1));
				
			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(rs);
			close(stmt);
		}
		return userPostCount;
	}
	
	
	public int updateLoginDate(String id, Connection conn) {
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "UPDATE TB_USER SET USER_LATESTLOGINDATE=SYSDATE WHERE USER_ID=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			result = stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return result;
	}

	public ArrayList<TagEntity> selectUserTagList(String userId, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<TagEntity> userTagList = new ArrayList<TagEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = " select USER_ID, T.TAG_ID, TAG_NAME FROM TB_USERTAG U , TB_TAG T  WHERE U.TAG_ID = T.TAG_ID  and user_id = ? ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			

			rs = stmt.executeQuery();
			
			while (rs.next()) {
				TagEntity tag = new TagEntity();
				tag.setTagId(rs.getString(2));
				tag.setTagName(rs.getString(3));
				userTagList.add(tag);

			}
			
		}catch(Exception e) {
			throw e;
			
		}finally {
			close(rs);
			close(stmt);
		}
		return userTagList;
	}


	public int insertUserTag(String userId, String tagId, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
	
		
		try {
			String sql = "INSERT INTO TB_USERTAG values(?,?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, userId);
			stmt.setString(2, tagId);

			result = stmt.executeUpdate();
			
		}catch(Exception e) {
			throw e;
			
		}finally {
			close(stmt);
		}
		return result;
		

	}
	
	
	public int insertUserTags(String userId, ArrayList<TagEntity> tags, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			for (TagEntity tag : tags) {
				String sql = "INSERT INTO TB_USERTAG values(?,?)";

				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, userId);
				stmt.setString(2, tag.getTagId());
				result = stmt.executeUpdate();
				
			}

			
		}catch(Exception e) {
			throw e;
			
		}finally {
			close(stmt);
			
		}
		return result;
	}

	public int deleteUserTag(String userId, String tagId, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "delete from tb_usertag where user_id = ? and tag_id= ? ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, tagId);
			result = stmt.executeUpdate();
			commit(conn);
			
		}catch(Exception e) {
			throw e;
			
		}finally {
			close(stmt);
		}
		return result;
	}

	public int deleteUserTags(String userId, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "delete from tb_usertag where user_id = ?  ";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			
			result = stmt.executeUpdate();
			commit(conn);
			
		}catch(Exception e) {
			throw e;
			
		}finally {
			close(stmt);
		}
		return result;
	}
	
	


	
}
