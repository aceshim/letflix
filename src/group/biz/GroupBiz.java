package group.biz;

import static common.Util.JdbcUtil.close;
import static common.Util.JdbcUtil.commit;
import static common.Util.JdbcUtil.getConnection;
import static common.Util.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import group.dao.GroupDao;
import group.entity.GroupEntity;
import group.entity.GroupListGroupMapEntity;

public class GroupBiz {

	public ArrayList<GroupListGroupMapEntity> selectGroupListGroup(String userId) throws SQLException {
		Connection conn = null;
		GroupDao dao = new GroupDao();
		
		ArrayList<GroupListGroupMapEntity> groupListGroup = null;
		try {
			conn = getConnection();
			groupListGroup = dao.selectGroupListGroup(userId, conn);
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}
		return groupListGroup;
	}
	
	// group 상세조회
	public GroupEntity selectGroup(String groupId) throws SQLException {

		GroupEntity group = new GroupEntity();
		GroupDao dao = new GroupDao();
		Connection conn = null;

		try {
			conn = getConnection();
			group = dao.selectGroup(groupId, conn);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return group;
	}

	// 그룹 리스트(목록) 조회
	public ArrayList<GroupEntity> selectGroupList() throws SQLException {

		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		GroupDao dao = new GroupDao();
		Connection conn = null;

		try {
			conn = getConnection();
			groupList = dao.selectGroupList(conn);
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn);
		}

		return groupList;
	}

	// 다음에 넣을 ID값
	public String nextGroupId() throws SQLException {

		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		GroupDao dao = new GroupDao();
		Connection conn = null;

		String result = "";

		try {
			conn = getConnection();
			groupList = dao.selectGroupList(conn);

			int nextnum = groupList.size() + 1;

			if (nextnum >= 100) {
				result = "g_00" + nextnum;
			} else if (nextnum >= 10) {
				result = "g_000" + nextnum;
			} else {
				result = "g_0000" + nextnum;
			}
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			close(conn);
		}

		return result;
	}

	// 그룹 생성
	public int insertGroup(GroupEntity group) throws SQLException {
		GroupDao dao = new GroupDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertGroup(group, conn);
			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			throw e;

		} finally {
			close(conn);
		}
		return result;
	}

	// 그룹 정보 수정, 관리자만 할 수 있게
	public int updateGroup(GroupEntity group) throws SQLException {
		GroupDao dao = new GroupDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.updateGroup(group, conn);
			commit(conn);

		} catch (Exception e) {
			rollback(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	// 그룹 삭제, 관리자만 할 수 있게
	public int deleteGroup(GroupEntity group) throws SQLException {
		GroupDao dao = new GroupDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deleteGroup(group, conn);
			commit(conn);

		} catch (Exception e) {
			rollback(conn);
			throw e;

		} finally {
			close(conn);
		}
		return result;
	}
}
