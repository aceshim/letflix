package user.entity;

public class UserEntity {
	private String userId;
	private String userPwd;
	private String userName;
	private String userTeam;
	private String userCompany;
	private String userPhone;
	private String userPic;
	private String userPosition;
	private String lastestLoginDate;

	public UserEntity(String userId, String userPwd, String userName, String userTeam, String userCompany,
			String userPhone, String userPic, String userPosition, String lastestLoginDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userTeam = userTeam;
		this.userCompany = userCompany;
		this.userPhone = userPhone;
		this.userPic = userPic;
		this.userPosition = userPosition;
		this.lastestLoginDate = lastestLoginDate;
	}

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}

	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String position) {
		this.userPosition = position;
	}

	public String getLastestLoginDate() {
		return lastestLoginDate;
	}

	public void setLastestLoginDate(String lastestLoginDate) {
		this.lastestLoginDate = lastestLoginDate;
	}

}
