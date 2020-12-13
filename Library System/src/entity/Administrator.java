package entity;

public class Administrator {
	private String sa_name;
	private String pwd; 
	public String getSa_name() {
		return sa_name;
	}
	public void setSa_name(String sa_name) {
		this.sa_name = sa_name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Administrator(String sa_name, String pwd) {
		super();
		this.sa_name = sa_name;
		this.pwd = pwd;
	}
	public Administrator() {
		
	}
	
}
