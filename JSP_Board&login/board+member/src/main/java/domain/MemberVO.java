package domain;

public class MemberVO {
	private String id;
	private String pwd;
	private String nick_name;
	private String reg_at;
	private String last_login;
	
	// login
	public MemberVO(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	// insert
	public MemberVO(String id, String pwd, String nick_name) {
		this(id,pwd);
		this.nick_name = nick_name;
	}
	
	// list
	public MemberVO(String id, String nick_name, String reg_at, String last_login) {
		this.id = id;
		this.nick_name = nick_name;
		this.reg_at = reg_at;
		this.last_login = last_login;
	}
	
	

	
	public MemberVO(String id, String pwd, String nick_name, String reg_at, String last_login) {
		this.id = id;
		this.pwd = pwd;
		this.nick_name = nick_name;
		this.reg_at = reg_at;
		this.last_login = last_login;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", nick_name=" + nick_name + ", reg_at=" + reg_at
				+ ", last_login=" + last_login + "]";
	}
	
}
