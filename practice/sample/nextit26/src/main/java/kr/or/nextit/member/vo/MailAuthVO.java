package kr.or.nextit.member.vo;

public class MailAuthVO {
	private String mail;
	private String authKey;
	private int isAuth;
	
	
	
	@Override
	public String toString() {
		return "MailAuthVO [mail=" + mail + ", authKey=" + authKey + ", isAuth=" + isAuth + "]";
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	public int getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}
	
}
