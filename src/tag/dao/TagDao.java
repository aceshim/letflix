package tag.dao;

import static common.Util.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import tag.entity.TagEntity;

public class TagDao {

	public ArrayList<TagEntity> selectAllTagList(Connection conn) {

		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT TAG_ID, TAG_NAME from TB_TAG";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {

				TagEntity tag = new TagEntity();
				tag.setTagId(rs.getString(1));
				tag.setTagName(rs.getString(2));

				tagList.add(tag);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(stmt);
		}
		return tagList;
	}

	public TagEntity selecTag(String tagId, Connection conn) {

		TagEntity tag = new TagEntity();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "	SELECT TAG_ID, TAG_NAME from TB_TAG where tag_id = ? ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, tagId);
			
			rs = stmt.executeQuery();

			if(rs.next())
			{
				tag.setTagId(rs.getString(1));
				tag.setTagName(rs.getString(2));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(stmt);
		}
		return tag;

	}

	
	// 25개 선택하는 쿼리
	public ArrayList<TagEntity> select25TagList(Connection conn) {

		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "	SELECT TAG_ID, TAG_NAME from TB_TAG ";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			for (int i = 0; i < 25; i++) {
				rs.next();
				TagEntity tag = new TagEntity();
				tag.setTagId(rs.getString(1));
				tag.setTagName(rs.getString(2));
				tagList.add(tag);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(stmt);
		}
		return tagList;

	}

	public int insertTag(TagEntity tag, Connection conn) {

		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "insert into TB_TAG values(?, ?)";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, tag.getTagId());
			stmt.setString(2, tag.getTagName());

			result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(stmt);
		}
		return result;

	}

	public int updateTag(TagEntity tag, Connection conn) throws Exception {

		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "update tb_tag set tag_name = ? where tag_id = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, tag.getTagName());
			stmt.setString(2, tag.getTagId());

			result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(stmt);
		}
		return result;

	}

	public int deleteTag(TagEntity tag, Connection conn) {

		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "delete from tb_tag where tag_id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tag.getTagId());
			result = stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(stmt);
		}
		return result;
	}

	public ArrayList<TagEntity> selectUserTag(String userId, Connection conn) {
		// TODO Auto-generated method stub
		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "	SELECT TAG_ID, TAG_NAME from TB_TAG JOIN TB_USERTAG USING(TAG_ID) WHERE USER_ID=?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, userId);

			rs = stmt.executeQuery();

			while(rs.next()) {
				TagEntity tag = new TagEntity();
				tag.setTagId(rs.getString(1));
				tag.setTagName(rs.getString(2));
				tagList.add(tag);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(rs);
			close(stmt);
		}
		return tagList;
	}

	public int insertTagByName(String tagName, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			String subSql = "SELECT * FROM TB_TAG WHERE TAG_NAME=?";
			stmt = conn.prepareStatement(subSql);

			stmt.setString(1, tagName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = 0;
				stmt.close();
			} else {
				stmt.close();
				subSql = "SELECT 'tag_'||LPAD(MAX(NVL(SUBSTR(tag_id, 5), 0)) + 1, 3, 0) FROM tb_tag";
				stmt = conn.prepareStatement(subSql);
				rs = stmt.executeQuery();
				String tagId = "";
				if (rs.next()) {
					tagId = rs.getString(1);
				}

				System.out.println("tagId: "+tagId);
				close(stmt);
				String sql = "insert into TB_TAG values(?, ?)";

				stmt = conn.prepareStatement(sql);
				stmt.setString(1, tagId);
				stmt.setString(2, tagName);

				result = stmt.executeUpdate();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close(stmt);
		}
		return result;
	}

	public int insertPptTag(String pptId, String tagName, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		int result = 0;

		try {
			String sql = "insert into TB_TAGUP values(?, (SELECT TAG_ID FROM TB_TAG WHERE TAG_NAME=?))";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, pptId);
			stmt.setString(2, tagName);

			result = stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			close(stmt);
		}
		return result;
	}

}
