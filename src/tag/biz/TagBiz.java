package tag.biz;

import static common.Util.JdbcUtil.close;
import static common.Util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tag.dao.TagDao;
import tag.entity.TagEntity;

public class TagBiz {

	// 태그 리스트(전체 목록) 조회
	public ArrayList<TagEntity> selectAllTagList() throws SQLException {

		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
		TagDao dao = new TagDao();
		Connection conn = null;

		try {
			conn = getConnection();
			tagList = dao.selectAllTagList(conn);
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			
			close(conn);
		}

		return tagList;
	}

	
	// 다음에 넣을 ID값
	public String nextTagId() throws SQLException {

		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
		TagDao dao = new TagDao();
		Connection conn = null;

		String result = "";

		try {
			conn = getConnection();
			tagList = dao.selectAllTagList(conn);

			int max = 0;

			for (int i = 0; i < tagList.size(); i++) {
				int temp = Integer.parseInt(tagList.get(i).getTagId().substring(5, 8));

				if (max < temp) {
					max = temp;
				}
			}

			if (max >= 100) {
				result = "tag_" + (max + 1);
			} else if (max >= 10) {
				result = "tag_0" + (max + 1);
			} else {
				result = "tag_00" + (max + 1);
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

	// 태그 아이디로 태그 조회
	public TagEntity selecTag(String tagId) throws SQLException {

		TagEntity tag = new TagEntity();
		TagDao dao = new TagDao();
		Connection conn = null;

		try {
			conn = getConnection();
			tag = dao.selecTag(tagId, conn);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {			
			close(conn);
		}

		return tag;
	}
	
	
	// 태그 리스트(25개 추천) 조회
	public ArrayList<TagEntity> select25TagList() throws SQLException {

		ArrayList<TagEntity> tagList = new ArrayList<TagEntity>();
		TagDao dao = new TagDao();
		Connection conn = null;

		try {
			conn = getConnection();
			tagList = dao.select25TagList(conn);
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
			
		} finally {
			close(conn);
		}

		return tagList;
	}

	// 태그 생성
	public int insertTag(TagEntity tag) throws SQLException {
		TagDao dao = new TagDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertTag(tag, conn);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			close(conn);
		}

		return result;
	}

	// 그룹 정보 수정, 관리자만 할 수 있게
	public int updateTag(TagEntity tag) throws SQLException {
		TagDao dao = new TagDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.updateTag(tag, conn);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			close(conn);
		}
		return result;
	}

	// 태그 삭제, 관리자만 할 수 있게
	public int deleteTag(TagEntity tag) throws SQLException {
		TagDao dao = new TagDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.deleteTag(tag, conn);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			close(conn);
		}

		return result;
	}


	public ArrayList<TagEntity> selectUserTag(String userId) {
		// TODO Auto-generated method stub
		TagDao dao = new TagDao();
		ArrayList<TagEntity> result = null;
		Connection conn = getConnection();
		try {
			result = dao.selectUserTag(userId, conn);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(conn);
		}

		return result;
	}


	public int insertTagByName(String tagName) throws SQLException {
		// TODO Auto-generated method stub
		TagDao dao = new TagDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertTagByName(tagName, conn);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			close(conn);
		}

		return result;
	}


	public int insertPptTag(String pptId, String tagName) throws SQLException {
		// TODO Auto-generated method stub
		TagDao dao = new TagDao();
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = dao.insertPptTag(pptId, tagName, conn);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		} finally {
			close(conn);
		}

		return result;
	}

}
