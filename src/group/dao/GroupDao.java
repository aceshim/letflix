package group.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static common.Util.JdbcUtil.*;

import java.util.ArrayList;

import group.entity.GroupEntity;
import group.entity.GroupListGroupMapEntity;
import user.entity.UserEntity;


public class GroupDao {

	public GroupEntity selectGroup(String groupId, Connection conn) {
		// TODO Auto-generated method stub
		GroupEntity group = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT GROUP_ID, GROUP_NAME, GROUP_CREATEDATE, GROUP_INVITATIONCODE, GROUP_DESCRIPTION, GROUP_THUMBNAIL, PRIVATE_YN "
					+ " from TB_GROUP where GROUP_ID = ?";
				

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupId);
	
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				group = new GroupEntity();
				group.setGroupId(rs.getString(1));
				group.setGroupName(rs.getString(2));
				group.setGroupCreateDate(rs.getString(3));
				group.setGroupInvitationCode(rs.getString(4));
				group.setGroupDescription(rs.getString(5));
				group.setGroupThumbnail(rs.getString(6));
				group.setPrivateYN(rs.getString(7));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(rs);
			close(stmt);
		}
		return group;
	}

	public ArrayList<GroupEntity> selectGroupList(Connection conn) {
		// TODO Auto-generated method stub
		
		ArrayList<GroupEntity> groupList = new ArrayList<GroupEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT GROUP_ID, GROUP_NAME , GROUP_CREATEDATE, "
					+ "GROUP_INVITATIONCODE, GROUP_DESCRIPTION, GROUP_THUMBNAIL, PRIVATE_YN from TB_GROUP ";
				
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				GroupEntity group = new GroupEntity();
				group.setGroupId(rs.getString(1));
				group.setGroupName(rs.getString(2));
				group.setGroupCreateDate(rs.getString(3));
				group.setGroupInvitationCode(rs.getString(4));
				group.setGroupDescription(rs.getString(5));
				group.setGroupThumbnail(rs.getString(6));
				group.setPrivateYN(rs.getString(7));
				groupList.add(group);

			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(rs);
			close(stmt);
		}
		return groupList;
		
		
	}
	
	 public int insertGroup(GroupEntity group, Connection conn) {
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "insert into tb_group values(?,?,?,?,?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, group.getGroupId());
			stmt.setString(2, group.getGroupName());
			stmt.setString(3, group.getGroupCreateDate());
			stmt.setString(4, group.getGroupInvitationCode());
			stmt.setString(5, group.getGroupDescription());
			
			result = stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(stmt);
		}
		return result;
		 
	 }

	public int deleteGroup(GroupEntity group, Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;
		
		try {
			String sql = "delete from tb_group where group_id = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, group.getGroupId());
			result = stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(stmt);
		}
		return result;
	}

	public int updateGroup(GroupEntity group, Connection conn) throws Exception{
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "update tb_group set group_name = ?, groupCreateDate = ?, "
					+ " groupInvitationCode = ?, groupDescription = ? where group_id = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, group.getGroupName());
			stmt.setString(2, group.getGroupCreateDate());
			stmt.setString(3, group.getGroupInvitationCode());
			stmt.setString(4, group.getGroupDescription());
			stmt.setString(5, group.getGroupId());
			
			result = stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			close(stmt);
		}
		return result;
		
	}

	public ArrayList<GroupListGroupMapEntity> selectGroupListGroup(String userId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<GroupListGroupMapEntity> groupListGroup = new ArrayList<>();
		try {
			String sql = "SELECT group_id, group_name, group_createdate, group_invitationcode, "
					+ "group_description, group_thumbnail, private_yn "
					+ "FROM tb_groupuser JOIN tb_group USING(group_id) WHERE user_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			
			groupListGroup.add(new GroupListGroupMapEntity("내 그룹"));
			groupListGroup.get(0).setList(selectGroupList(userId, stmt, conn));
			
			sql = "SELECT group_id, group_name, group_createdate, group_invitationcode, "
					+ "group_description, group_thumbnail, private_yn "
					+ "FROM tb_group WHERE group_id NOT IN (SELECT group_id FROM tb_groupuser WHERE user_id=?) "
					+ "ORDER BY group_createdate DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			groupListGroup.add(new GroupListGroupMapEntity("추천 그룹"));
			groupListGroup.get(1).setList(selectGroupList(userId, stmt, conn));
			
		} catch(SQLException e) {
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return groupListGroup;
	}

	private ArrayList<GroupEntity> selectGroupList(String userId, PreparedStatement stmt, Connection conn) throws SQLException {
		PreparedStatement subStmt = null;
		ResultSet subRs = null;
		ResultSet rs = null;
		
		ArrayList<GroupEntity> groupList = new ArrayList<>();
		try {
			String subSql = "SELECT COUNT(*) FROM tb_groupuser WHERE group_id=?";
			rs = stmt.executeQuery();
			while(rs.next()) {
				String groupId = rs.getString("group_id");
				String groupName = rs.getString("group_name");
				String groupCreateDate = rs.getString("group_createdate");
				String groupInvitationCode = rs.getString("group_invitationcode");
				String groupDescription = rs.getString("group_description");
				String groupThumbnail = rs.getString("group_thumbnail");
				int groupMemberNum = -1;
				subStmt = conn.prepareStatement(subSql);
				subStmt.setString(1, groupId);
				subRs = subStmt.executeQuery();
				if (subRs.next()) {
					groupMemberNum = subRs.getInt(1);
				}
				String privateYn = rs.getString("private_yn");
				groupList.add(new GroupEntity(groupId, groupName, groupCreateDate, groupInvitationCode, groupDescription, groupThumbnail, groupMemberNum, privateYn));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(subRs);
			close(subStmt);
		}
		
		return groupList;
	}
}
