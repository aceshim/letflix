package search.dao;

import static common.Util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import group.entity.GroupEntity;
import post.entity.PostEntity;
import tag.entity.TagEntity;

public class SearchDao {

	public ArrayList<PostEntity> searchPostByTagBtn(String tagId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<PostEntity> searchPostByTag = new ArrayList<PostEntity>();

		try {
			String sql = "SELECT post_id, user_id, user_name, user_company, user_position, group_id, group_name, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
					    +"from(select tag_id, post_id, user_id, group_id, group_name, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
						     +"from(select tag_id, post_id, user_id, group_id, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
						          +"from (select tag_id, ppt_id, ppt_length "
						                +"from (select * " 
						                      +"from tb_tagup " 
						                      +"where tag_id = '"+ tagId +"') "
						                +"left join tb_ppt using(ppt_id) ) "
						          +"left join tb_post using(ppt_id) ) "     
						     +"left join tb_group using (group_id) ) "
						+"left join tb_user using (user_id) "
						+"ORDER BY post_createdate DESC";
			
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, postName);
			rs = stmt.executeQuery();

			while (rs.next()) {

				PostEntity post = new PostEntity();
				post.setPostId(rs.getString(1));
				post.setUserId(rs.getString(2));
				post.setUserName(rs.getString(3));
				post.setUserCompany(rs.getString(4));
				post.setUserPosition(rs.getString(5));
				post.setGroupId(rs.getString(6));
				post.setGroupName(rs.getString(7));
				post.setPptId(rs.getString(8));
				post.setTitle(rs.getString(9));
				post.setLength(Integer.parseInt(rs.getString(10)));
				post.setCreateDate(rs.getString(11));
				post.setThumbnail(rs.getString(12));
				post.setDescription(rs.getString(13));
				ArrayList<TagEntity> tagList = searchTagList(rs.getString(8), conn);
				post.setTagList(tagList);

				searchPostByTag.add(post);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}

		return searchPostByTag;
	}
	
	public ArrayList<PostEntity> searchPostByTag(String tagName, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<PostEntity> searchPostByTag = new ArrayList<PostEntity>();

		try {
			String sql = "SELECT post_id, user_id, user_name, user_company, user_position, group_id, group_name, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
					    +"from(select tag_id, post_id, user_id, group_id, group_name, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
						     +"from(select tag_id, post_id, user_id, group_id, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
						          +"from (select tag_id, ppt_id, ppt_length "
						                +"from (select * " 
						                      +"from tb_tag " 
						                      +"left join tb_tagup using (tag_id) "
						                      +"where tag_name like '%"+ tagName +"%') "
						                +"left join tb_ppt using(ppt_id) ) "
						          +"left join tb_post using(ppt_id) ) "     
						     +"left join tb_group using (group_id) ) "
						+"left join tb_user using (user_id) "
						+"ORDER BY post_createdate DESC";
			
			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, postName);
			rs = stmt.executeQuery();

			while (rs.next()) {

				PostEntity post = new PostEntity();
				post.setPostId(rs.getString(1));
				post.setUserId(rs.getString(2));
				post.setUserName(rs.getString(3));
				post.setUserCompany(rs.getString(4));
				post.setUserPosition(rs.getString(5));
				post.setGroupId(rs.getString(6));
				post.setGroupName(rs.getString(7));
				post.setPptId(rs.getString(8));
				post.setTitle(rs.getString(9));
				post.setLength(Integer.parseInt(rs.getString(10)));
				post.setCreateDate(rs.getString(11));
				post.setThumbnail(rs.getString(12));
				post.setDescription(rs.getString(13));
				ArrayList<TagEntity> tagList = searchTagList(rs.getString(8), conn);
				post.setTagList(tagList);

				searchPostByTag.add(post);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}

		return searchPostByTag;
	}
	
	
	
	
	public ArrayList<PostEntity> searchByPostName(String postName, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<PostEntity> searchByPost = new ArrayList<PostEntity>();

		try {
			String sql = "SELECT post_id, user_id, user_name, user_company, user_position, group_id, group_name, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
					    +"FROM(SELECT tag_id, post_id, user_id, group_id, group_name, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
						     +"FROM(SELECT tag_id, post_id, user_id, group_id, ppt_id, post_title, ppt_length, post_createdate, post_thumbnail, post_description "
						          +"FROM (SELECT tag_id, ppt_id, ppt_length "
						                +"FROM tb_tagup " 
						                +"LEFT JOIN tb_ppt USING(ppt_id)) "
						          +"LEFT JOIN tb_post USING(ppt_id)) "
						     +"LEFT JOIN tb_group USING(group_id)) "
						+"LEFT JOIN tb_user USING(user_id) "
						+"WHERE post_title like '%"+postName+"%' "
						+"ORDER BY post_createdate DESC";

			stmt = conn.prepareStatement(sql);
			//stmt.setString(1, postName);
			rs = stmt.executeQuery();

			while (rs.next()) {

				PostEntity post = new PostEntity();
				post.setPostId(rs.getString(1));
				post.setUserId(rs.getString(2));
				post.setUserName(rs.getString(3));
				post.setUserCompany(rs.getString(4));
				post.setUserPosition(rs.getString(5));
				post.setGroupId(rs.getString(6));
				post.setGroupName(rs.getString(7));
				post.setPptId(rs.getString(8));
				post.setTitle(rs.getString(9));
				post.setLength(Integer.parseInt(rs.getString(10)));
				post.setCreateDate(rs.getString(11));
				post.setThumbnail(rs.getString(12));
				post.setDescription(rs.getString(13));
				ArrayList<TagEntity> tagList = searchTagList(rs.getString(8), conn);
				post.setTagList(tagList);

				searchByPost.add(post);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}

		return searchByPost;
	}

	public ArrayList<TagEntity> searchByTagName(String tagName, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<TagEntity> searchByTagName = new ArrayList<TagEntity>();

		try {
			String sql = "SELECT tag_id, tag_name FROM tb_tag WHERE tag_name like '%"+tagName+ "%' ";
	
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tagName);
			rs = stmt.executeQuery();

			while (rs.next()) {

				TagEntity tag = new TagEntity();
				tag.setTagId(rs.getString(1));
				tag.setTagName(rs.getString(2));
	
				searchByTagName.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}

		return searchByTagName;
	}

	public ArrayList<GroupEntity> searchBygroupName(String groupName, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<GroupEntity> searchBygroupName = new ArrayList<GroupEntity>();

		try {
			String sql = "SELECT group_id, group_name, group_createdate, group_invitationcode, group_description, group_thumbnail, private_yn " 
						+"FROM tb_group " 
					    +"WHERE group_name like '%"+groupName+"%' ";
	
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupName);
			rs = stmt.executeQuery();

			
			while (rs.next()) {

				GroupEntity group = new GroupEntity();
				group.setGroupId(rs.getString(1));
				group.setGroupName(rs.getString(2));
				group.setGroupCreateDate(rs.getString(3));
				group.setGroupInvitationCode(rs.getString(4));
				group.setGroupDescription(rs.getString(5));
				group.setGroupThumbnail(rs.getString(6));
				group.setMemberNum(Integer.parseInt(groupMemberNum(rs.getString(1), conn)));
				group.setPrivateYN(rs.getString(7));
				
				searchBygroupName.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}

		return searchBygroupName;
	}
	
	
	private String groupMemberNum(String groupId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String result = "";

		try {
			String sql = "SELECT count(*) FROM tb_groupuser WHERE group_id=? GROUP BY group_id";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, groupId);

			rs = stmt.executeQuery();

			while (rs.next()) {

				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return result;
	}
	
	private ArrayList<TagEntity> searchTagList(String pptId, Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();

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
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(rs);
			close(stmt);
		}
		return tagList;
	}
}
