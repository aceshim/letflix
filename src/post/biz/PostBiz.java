package post.biz;

import static common.Util.JdbcUtil.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import group.dao.GroupDao;
import group.entity.GroupEntity;
import post.dao.PostDao;
import post.entity.PostEntity;
import post.entity.PostListGroupMapEntity;
import tag.dao.TagDao;
import tag.entity.TagEntity;

public class PostBiz {

	public ArrayList<TagEntity> selectTagListByPptId(String pptId) throws SQLException {
		Connection conn = null;
		
		PostDao dao = new PostDao();
		
		ArrayList<TagEntity> tags = new ArrayList<>();
		
		try {
			conn = getConnection();
			tags = dao.selectTagListByPptId(pptId, conn);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
		return tags;
	}
	
	public ArrayList<PostListGroupMapEntity> selectPostListGroup(String userId) throws SQLException {

		Connection conn = null;
		PostDao dao = new PostDao();

		ArrayList<PostListGroupMapEntity> postListGroup = null;

		try {
			conn = getConnection();
			postListGroup = dao.selectPostListGroup(userId, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return postListGroup;
	}

	// post 상세조회
	public PostEntity selectPost(String postId) throws SQLException {

		PostEntity post = new PostEntity();
		PostDao dao = new PostDao();
		Connection conn = null;

		try {
			conn = getConnection();
			post = dao.selectPost(postId, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return post;
	}

	public int insertPost(PostEntity post) throws SQLException {
		PostDao dao = new PostDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertPost(post, conn);
			conn.commit();
		}catch(SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}


	public int deletePost(String postId) throws SQLException {
		PostDao dao = new PostDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deletePost(postId, conn);
			conn.commit();
		}catch(SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}
	
	
	public int updatePost(PostEntity post) throws SQLException {
		PostDao dao = new PostDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.updatePost(post, conn);
			conn.commit();
		}catch(SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

	public PostEntity selectPostByPptId(String pptId) throws SQLException {
		PostDao dao = new PostDao();
		Connection conn = null;
		PostEntity post = null;
		
		try {
			conn = getConnection();
			post = dao.selctPostByPptId(pptId, conn);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
		return post;
	}
	
	public int insertReadHistory(String userId, String postId) throws SQLException{
		PostDao dao = new PostDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertReadHistory(userId,postId, conn);
			conn.commit();
		}catch(SQLException e) {
			conn.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			close(conn);
		}
		return result;
	}

}
