package user.biz;

import static common.Util.JdbcUtil.close;
import static common.Util.JdbcUtil.commit;
import static common.Util.JdbcUtil.getConnection;
import static common.Util.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import group.dao.GroupDao;
import tag.entity.TagEntity;
import user.dao.UserDao;
import user.entity.UserEntity;

public class UserBiz {
	public UserEntity login(String id, String pwd) throws SQLException {
		Connection conn = null;
		UserDao dao = new UserDao();
		UserEntity user = null;
		int result = 0;

		try {
			conn = getConnection();
			user = dao.login(id, pwd, conn);
			result = dao.updateLoginDate(id, conn);
			if (result == 0) {
				throw new SQLException("아무것도 안들어갔어요");
			} else {
				conn.commit();
			}
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			close(conn);
		}

		return user;
	}


	public ArrayList<TagEntity> selectUserTagList(String userId) throws SQLException {

		ArrayList<TagEntity> userTagList = new ArrayList<TagEntity>();
		UserDao dao = new UserDao();
		Connection conn = null;

		try {
			conn = getConnection();
			userTagList = dao.selectUserTagList(userId, conn);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}

		return userTagList;
	}

	public UserEntity getUserProfile(String id) {
		Connection conn = null;
		UserDao dao = new UserDao();
		UserEntity user = null;
	

		try {
			conn = getConnection();
			user = dao.getUserProfile(id,conn);

		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return user;
	}

	public int getUserPostCount(String id) {
		int userPostCount = 0;
		UserDao dao = new UserDao();
		Connection conn = null;

		try {
			conn = getConnection();
			userPostCount = dao.getUserPostCount(id, conn);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}

		return userPostCount;
	}


	public int getUserGroupCount(String id) {
		int userGroupCount = 0;
		UserDao dao = new UserDao();
		
		Connection conn = null;

		try {
			conn = getConnection();
			userGroupCount = dao.getUserGroupCount(id, conn);
		}catch(Exception e) {
			throw e;
		}finally {
			close(conn);
		}

		return userGroupCount;
	}



	public int updateUserTag(String userId, ArrayList<TagEntity> tags) throws SQLException {
		//userId가 가지고 있는 tag 모두 delete 하고 다시 insert 
		UserDao dao = new UserDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deleteUserTags(userId, conn);


			result = dao.insertUserTags(userId, tags, conn);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
		}



		finally {
			close(conn);
		}
		return result;


	}

	public int insertUserTag(String userId, String tagId) throws SQLException {
		UserDao dao = new UserDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertUserTag(userId,tagId, conn);
			commit(conn);

		} catch(Exception e) {
			rollback(conn);
			e.printStackTrace();


		} finally {
			close(conn);
		}
		return result;
	}


	public int deleteUserTag(String userId, String tagId) throws SQLException{
		UserDao dao = new UserDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deleteUserTag(userId,tagId, conn);
			commit(conn);

		}catch(Exception e) {
			rollback(conn);
			e.printStackTrace();

		}finally {
			close(conn);
		}
		return result;
	}
}
