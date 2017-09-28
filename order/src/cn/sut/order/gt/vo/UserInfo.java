package cn.sut.order.gt.vo;

public class UserInfo {
	private int userId;
	private String userAccount;
	private String userPass;
	private int role;
	private int locked;
	private String faceimg;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public String getFaceimg() {
		return faceimg;
	}
	public void setFaceimg(String faceimg) {
		this.faceimg = faceimg;
	}
	
	
	

}
