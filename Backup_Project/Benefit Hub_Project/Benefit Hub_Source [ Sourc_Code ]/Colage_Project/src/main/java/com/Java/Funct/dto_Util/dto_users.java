package com.Java.Funct.dto_Util;

public class dto_users {
	private String u_num, u_id, u_pw, u_nm, u_addr, u_sec_n1, u_sec_n2;

	public dto_users(String u_num, String u_id, String u_pw, String u_addr, String u_nm, String u_sec_n1, String u_sec_n2) {
		this.u_num = u_num;
		this.u_id = u_id;
		this.u_nm = u_nm;
		this.u_pw = u_pw;
		this.u_addr = u_addr;
		this.u_sec_n1 = u_sec_n1;
		this.u_sec_n2 = u_sec_n2; }

	public String getU_num() { return u_num; }
	public String getU_id() { return u_id; }
	public String getU_pw() { return u_pw; }
	public String getU_nm() { return u_nm; }
	public String getU_addr() { return u_addr; }
	public String getU_sec_n1() { return u_sec_n1; }
	public String getU_sec_n2() { return u_sec_n2; }
}
