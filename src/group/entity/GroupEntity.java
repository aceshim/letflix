package group.entity;

public class GroupEntity {
	private String groupId;
	private String groupName;
	private String groupCreateDate;
	private String groupInvitationCode;
	private String groupDescription;
	private String groupThumbnail;
	private int memberNum;
	private String privateYN;
	
	public GroupEntity() {
		
	}
	
	public GroupEntity(String groupId, String groupName, String groupCreateDate, String groupInvitationCode,
			String groupDescription, String groupThumbnail, int memberNum, String privateYN) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupCreateDate = groupCreateDate;
		this.groupInvitationCode = groupInvitationCode;
		this.groupDescription = groupDescription;
		this.groupThumbnail = groupThumbnail;
		this.memberNum = memberNum;
		this.privateYN = privateYN;
	}

	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupCreateDate() {
		return groupCreateDate;
	}
	public void setGroupCreateDate(String groupCreateDate) {
		this.groupCreateDate = groupCreateDate;
	}
	public String getGroupInvitationCode() {
		return groupInvitationCode;
	}
	public void setGroupInvitationCode(String groupInvitationCode) {
		this.groupInvitationCode = groupInvitationCode;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	public String getGroupThumbnail() {
		return groupThumbnail;
	}
	public void setGroupThumbnail(String groupThumbnail) {
		this.groupThumbnail = groupThumbnail;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getPrivateYN() {
		return privateYN;
	}
	public void setPrivateYN(String privateYN) {
		this.privateYN = privateYN;
	}
	
}
