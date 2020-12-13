package entity;

import java.util.Date;

/*
 * administrator(*a_id,username,pwd)
reader(*r_id,username,pwd,can_lend,has_lent,wrong)
reader_person(*r_id,p_id)
person(*p_id,name,sex,birth,position,addr,tele,dep)
 * 
 * */

//r_id 是账号，p_id是身份证号码

public class Reader {
	private String r_id,pwd,p_id,name,sex,position,addr,tele,dep;
	private int can_lend,has_lent,wrong;
	private Date birth;

	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public int getCan_lend() {
		return can_lend;
	}
	public void setCan_lend(int can_lend) {
		this.can_lend = can_lend;
	}
	public int getHas_lent() {
		return has_lent;
	}
	public void setHas_lent(int has_lent) {
		this.has_lent = has_lent;
	}
	public int getWrong() {
		return wrong;
	}
	public void setWrong(int wrong) {
		this.wrong = wrong;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Reader(String r_id,String pwd, String p_id, String name, String sex, String position,
			String addr, String tele, String dep, int can_lend, int has_lent, int wrong, Date birth) {
		super();
		this.r_id = r_id;
		this.pwd = pwd;
		this.p_id = p_id;
		this.name = name;
		this.sex = sex;
		this.position = position;
		this.addr = addr;
		this.tele = tele;
		this.dep = dep;
		this.can_lend = can_lend;
		this.has_lent = has_lent;
		this.wrong = wrong;
		this.birth = birth;
	}
	public Reader() {
		can_lend = 5;
		has_lent =	0;
		wrong = 0;
	}
	@Override
	public String toString() {
		return "Reader [r_id=" + r_id + ", pwd=" + pwd + ", p_id=" + p_id + ", name=" + name + ", sex=" + sex
				+ ", position=" + position + ", addr=" + addr + ", tele=" + tele + ", dep=" + dep + ", can_lend="
				+ can_lend + ", has_lent=" + has_lent + ", wrong=" + wrong + ", birth=" + birth + "]";
	}
	
}
