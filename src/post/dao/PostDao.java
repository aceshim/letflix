package post.dao;

import static common.Util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import post.entity.PostEntity;
import post.entity.PostListGroupMapEntity;
import ppt.entity.PptEntity;
import tag.entity.TagEntity;

public class PostDao {

	public ArrayList<PostListGroupMapEntity> selectPostListGroup(String userId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<PostListGroupMapEntity> postListGroup = new ArrayList<>();
		
		try {
			String sql = "SELECT post_id, user_id, user_name, user_company, "
					+ "user_position, group_id, group_name, ppt_id, post_title, post_createdate, "
					+ "post_thumbnail, post_description "
					+ "FROM (tb_post JOIN tb_user USING(user_id)) JOIN tb_group USING(group_id) "
					+ "WHERE user_id=? ORDER BY post_createdate DESC";		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			
			postListGroup.add(new PostListGroupMapEntity("내가 쓴 글"));
			postListGroup.get(0).setList(selectPostList(stmt, conn));
			
			// TODO: 추천 기준
			sql = "SELECT post_id, user_id, user_name, user_company, "
					+ "user_position, group_id, group_name, ppt_id, post_title, post_createdate, "
					+ "post_thumbnail, post_description "
					+ "FROM (tb_post JOIN tb_user USING(user_id)) JOIN tb_group USING(group_id)";
			stmt = conn.prepareStatement(sql);
			
			postListGroup.add(new PostListGroupMapEntity("추천 글"));
			postListGroup.get(1).setList(selectPostList(stmt, conn));
			
			sql = "SELECT post_id, rh.user_id, user_name, user_company, "
					+ "user_position, group_id, group_name, ppt_id, post_title, post_createdate, "
					+ "post_thumbnail, post_description "
					+ "FROM tb_readhistory  rh "
					+ "JOIN ((tb_post JOIN tb_user USING(user_id)) JOIN tb_group USING(group_id)) USING(post_id) "
					+ "WHERE rh.user_id=? "
					+ "ORDER BY readhistory_readdate DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			postListGroup.add(new PostListGroupMapEntity("내가 읽은 글"));
			postListGroup.get(2).setList(selectPostList(stmt, conn));
			
			sql = "SELECT tag_id, tag_name FROM tb_usertag JOIN tb_tag USING(tag_id) WHERE user_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			rs = stmt.executeQuery();
			while(rs.next()) {				
				postListGroup.add(new PostListGroupMapEntity("태그  별 추천 #" + rs.getString(2)));
				sql = "SELECT post_id, user_id, user_name, user_company, "
						+ "user_position, group_id, group_name, ppt_id, post_title, post_createdate, "
						+ "post_thumbnail, post_description "
						+ "FROM (tb_post JOIN tb_user USING(user_id)) JOIN tb_group USING(group_id) "
						+ "WHERE ppt_id IN (SELECT ppt_id FROM tb_ppt JOIN tb_tagup USING(ppt_id) WHERE tag_id=?) ORDER BY post_createdate DESC";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, rs.getString(1));
				postListGroup.get(postListGroup.size() - 1).setList(selectPostList(stmt, conn));
			}
			
//			sql = "SELECT post_id, user_id, user_name, user_company, "
//					+ "user_position, group_id, group_name, ppt_id, post_title, post_createdate, "
//					+ "post_thumbnail, post_description "
//					+ "FROM (tb_post JOIN tb_user USING(user_id)) JOIN tb_group USING(group_id) "
//					+ "WHERE group_id IN (SELECT group_id FROM tb_groupuser WHERE user_id=?) ORDER BY post_createdate DESC, group_id";
//			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, userId);
//			postListGroup.add(new PostListGroupMapEntity("내 그룹의 글"));
//			postListGroup.get(postListGroup.size() - 1).setList(selectPostList(stmt, conn));
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		
		return postListGroup;
	}
	
	private ArrayList<PostEntity> selectPostList(PreparedStatement stmt, Connection conn) throws SQLException {
		ResultSet rs = null;
		
		ArrayList<PostEntity> postList = new ArrayList<>();
		try {
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String postId = rs.getString("post_id");
				String userId = rs.getString("user_id");
				String userName = rs.getString("user_name");
				String userCompany = rs.getString("user_company");
				String userPosition = rs.getString("user_position");
				String groupId = rs.getString("group_id");
				String groupName = rs.getString("group_name");
				String pptId = rs.getString("ppt_id");
				String title = rs.getString("post_title");
				Date date = rs.getDate("post_createdate");
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				String createDate = sdf.format(date);
				String thumbnail = rs.getString("post_thumbnail");
				String description = rs.getString("post_description");	
				ArrayList<TagEntity> tagList = selectTagList(pptId, conn);
				
				postList.add(new PostEntity(postId, userId, userName, userCompany, userPosition, groupId, groupName, pptId, title, createDate, thumbnail, description, tagList));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
		}
		return postList;
	}

	public ArrayList<TagEntity> selectTagListByPptId(String pptId, Connection conn) throws SQLException {
		return selectTagList(pptId, conn);
	}
	
	private ArrayList<TagEntity> selectTagList(String pptId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<TagEntity> tagList = new ArrayList<>();
		
		try {
			String sql = "SELECT * FROM tb_tagup JOIN tb_tag USING(tag_id) WHERE ppt_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String tagId = rs.getString("tag_id");
				String tagName = rs.getString("tag_name");
				
				tagList.add(new TagEntity(tagId, tagName));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return tagList;
	}
	
	public PostEntity selectPost(String postId, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PostEntity post = null;
		
		try {
			String sql = "SELECT post_id, user_id, user_name, user_company, " + 
					"user_position, group_id, group_name, ppt_id, post_title, post_createdate, " + 
					"post_thumbnail, post_description, ppt_length " + 
					"FROM ((tb_post JOIN tb_user USING(user_id)) JOIN tb_group USING(group_id)) JOIN (SELECT ppt_id, ppt_length FROM tb_ppt) USING(ppt_id) " + 
					"WHERE post_id=?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, postId);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String userId = rs.getString("USER_ID");
				String userName = rs.getString("USER_NAME");
				String userCompany = rs.getString("USER_COMPANY");
				String userPosition = rs.getString("USER_POSITION");
				String groupId = rs.getString("GROUP_ID");
				String groupName = rs.getString("GROUP_NAME");
				String pptId = rs.getString("PPT_ID");
				String postTitle = rs.getString("POST_TITLE");
				String postCreateDate = rs.getString("POST_CREATEDATE");
				String postThumbnail = rs.getString("POST_THUMBNAIL");
				String postDescription = rs.getString("POST_DESCRIPTION");
				int postLength = rs.getInt("PPT_LENGTH");
				ArrayList<TagEntity> tagList = selectTagList(pptId, conn);
				post = new PostEntity(postId, userId, userName, userCompany, userPosition, groupId, 
						groupName, pptId, postTitle, postCreateDate, postThumbnail, postDescription, tagList);
				post.setLength(postLength);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return post;
	}
	
	
	public int insertPost(PostEntity post, Connection conn) throws SQLException {
		post.setTagList(selectTagList(post.getPptId(), conn));
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			String subSql = "SELECT 'p_'||LPAD(MAX(NVL(SUBSTR(post_id, 3), 0)) + 1, 5, 0) FROM tb_post";
			stmt = conn.prepareStatement(subSql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				post.setPostId(rs.getString(1));
			}
			close(stmt);
			String sql = "insert into tb_post values(?,?,'g_00001',?,?,SYSDATE,'01.JPG',?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getPostId());
			stmt.setString(2, post.getUserId());
			stmt.setString(3, post.getPptId());
			stmt.setString(4, post.getTitle());
			stmt.setString(5, post.getDescription());

			System.out.println(post.getPostId());
			System.out.println(post.getUserId());
			System.out.println(post.getPptId());
			System.out.println(post.getTitle());
			System.out.println(post.getDescription());
		
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return result;
	}
	
	public int deletePost(String postId, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			deleteReadHistory(postId, conn);
			String sql = "delete from tb_post where post_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, postId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(stmt);
		}
		return result;
	}
	
	private int deleteReadHistory(String postId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "DELETE FROM tb_readhistory WHERE post_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, postId);
			result = stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(stmt);
		}
		return result;
	}

	public int updatePost(PostEntity post, Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "update tb_post SET ppt_id = ?, post_title = ?, "
					+ " post_createdate = SYSDATE, post_description = ? where post_id = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, post.getPptId());
			stmt.setString(2, post.getTitle());
			stmt.setString(3, post.getDescription());
			stmt.setString(4, post.getPostId());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(stmt);
		}
		return result;
	}

	public PostEntity selctPostByPptId(String pptId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		PostEntity post = new PostEntity();
		
		try {
			String sql = "SELECT post_id, user_id, ppt_id, post_title, post_createdate, post_thumbnail, post_description FROM tb_post WHERE ppt_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				post.setPostId(rs.getString("post_id"));
				post.setUserId(rs.getString("user_id"));
				post.setPptId(rs.getString("ppt_id"));
				post.setTitle(rs.getString("post_title"));
				post.setCreateDate(rs.getString("post_createdate"));
				post.setThumbnail(rs.getString("post_thumbnail"));
				post.setDescription(rs.getString("post_description"));
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return post;
		
	}

	public int insertReadHistory(String userId, String postId, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "insert into tb_readhistory values(?,?,sysdate)";
			
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setString(2, postId);
			
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return result;
	}
	

	
}
