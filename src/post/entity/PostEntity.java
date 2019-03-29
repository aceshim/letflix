package post.entity;

import java.util.ArrayList;
import tag.entity.TagEntity;

public class PostEntity {
	private String postId;
	private String userId;
	private String userName;
	private String userCompany;
	private String userPosition;
	private String groupId;
	private String groupName;
	private String pptId;
	private String title;
	private int length;
	private String createDate;
	private String thumbnail;
	private String description;
	private ArrayList<TagEntity> tagList;
	

	
	public PostEntity() {
		
	}

	public PostEntity(String postId, String userId, String userName, String userCompany, String userPosition,
			String groupId, String groupName, String pptId, String title, String createDate, String thumbnail,
			String description, ArrayList<TagEntity> tagList) {
		this.postId = postId;
		this.userId = userId;
		this.userName = userName;
		this.userCompany = userCompany;
		this.userPosition = userPosition;
		this.groupId = groupId;
		this.groupName = groupName;
		this.pptId = pptId;
		this.title = title;
		this.createDate = createDate;
		this.thumbnail = thumbnail;
		this.description = description;
		this.tagList = tagList;
	}



	public ArrayList<TagEntity> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<TagEntity> tagList) {
		this.tagList = tagList;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getPptId() {
		return pptId;
	}

	public void setPptId(String pptId) {
		this.pptId = pptId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserName() {
		return userName;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
