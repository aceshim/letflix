package search.biz;

import static common.Util.JdbcUtil.close;
import static common.Util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import group.entity.GroupEntity;
import post.entity.PostEntity;
import search.dao.SearchDao;
import tag.entity.TagEntity;

public class SearchBiz {
	
	public ArrayList<PostEntity> searchPostByTagBtn(String tagId) throws SQLException {

		Connection conn = null;
		SearchDao dao = new SearchDao();

		ArrayList<PostEntity> searchPostByTagBtn = null;

		try {
			conn = getConnection();
			searchPostByTagBtn = dao.searchPostByTagBtn(tagId, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return searchPostByTagBtn;
	}
	
	public ArrayList<PostEntity> searchPostByTag(String tagName) throws SQLException {

		Connection conn = null;
		SearchDao dao = new SearchDao();

		ArrayList<PostEntity> searchPostByTag = null;

		try {
			conn = getConnection();
			searchPostByTag = dao.searchPostByTag(tagName, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return searchPostByTag;
	}
	
	public ArrayList<PostEntity> searchByPostName(String postName) throws SQLException {

		Connection conn = null;
		SearchDao dao = new SearchDao();

		ArrayList<PostEntity> searchByPostName = null;

		try {
			conn = getConnection();
			searchByPostName = dao.searchByPostName(postName, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return searchByPostName;
	}
	
	public ArrayList<TagEntity> searchByTagName(String tagName) throws SQLException {

		Connection conn = null;
		SearchDao dao = new SearchDao();

		ArrayList<TagEntity> searchByTagName = null;

		try {
			conn = getConnection();
			searchByTagName = dao.searchByTagName(tagName, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return searchByTagName;
	}
	
	public ArrayList<GroupEntity> searchBygroupName(String groupName) throws SQLException {

		Connection conn = null;
		SearchDao dao = new SearchDao();

		ArrayList<GroupEntity> searchBygroupName = null;

		try {
			conn = getConnection();
			searchBygroupName = dao.searchBygroupName(groupName, conn);

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			close(conn);
		}

		return searchBygroupName;
	}

}
