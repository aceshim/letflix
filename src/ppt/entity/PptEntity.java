package ppt.entity;

import java.util.ArrayList;

import tag.entity.TagEntity;

public class PptEntity {
	private String pptId;
	private String userId;
	private String pptDate;
	private String pptLocation;
	private int pptLength;
	private int pptLike;
	private int pptViews;
	private String pptTitle;
	private ArrayList<TagEntity> tagList;
	

	public PptEntity() {}
	
	public PptEntity(String pptId, String userId, String pptDate, String pptLocation, int pptLength, int pptLike, int pptViews) {
		this.pptId = pptId;
		this.userId = userId;
		this.pptDate = pptDate;
		this.pptLocation = pptLocation;
		this.pptLength = pptLength;
		this.pptLike = pptLike;
		this.pptViews = pptViews;
	}
	
	public PptEntity(String pptId, String userId, String pptDate, String pptLocation, int pptLength, int pptLike,
			int pptViews, String pptTitle) {
		super();
		this.pptId = pptId;
		this.userId = userId;
		this.pptDate = pptDate;
		this.pptLocation = pptLocation;
		this.pptLength = pptLength;
		this.pptLike = pptLike;
		this.pptViews = pptViews;
		this.pptTitle = pptTitle;
	}

	public String getPptTitle() {
		return pptTitle;
	}

	public void setPptTitle(String pptTitle) {
		this.pptTitle = pptTitle;
	}

	public String getPptId() {
		return pptId;
	}
	public void setPptId(String pptId) {
		this.pptId = pptId;
	}
	public String getPptDate() {
		return pptDate;
	}
	public void setPptDate(String pptDate) {
		this.pptDate = pptDate;
	}
	public String getPptLocation() {
		return pptLocation;
	}
	public void setPptLocation(String pptLocation) {
		this.pptLocation = pptLocation;
	}
	public int getPptLength() {
		return pptLength;
	}
	public void setPptLength(int pptLength) {
		this.pptLength = pptLength;
	}
	public int getPptLike() {
		return pptLike;
	}
	public void setPptLike(int pptLike) {
		this.pptLike = pptLike;
	}
	public int getPptViews() {
		return pptViews;
	}
	public void setPptViews(int pptViews) {
		this.pptViews = pptViews;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

	public ArrayList<TagEntity> getTagList() {
		return tagList;
	}

	public void setTagList(ArrayList<TagEntity> tagList) {
		this.tagList = tagList;
	}
}
