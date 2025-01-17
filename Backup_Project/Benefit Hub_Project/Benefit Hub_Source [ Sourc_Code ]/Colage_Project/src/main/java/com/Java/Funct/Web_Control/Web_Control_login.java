package com.Java.Funct.Web_Control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Java.Funct.DB_Map.SQL_Map;

@Controller
@RequestMapping("Login_more")
public class Web_Control_login {
	@Autowired
	SQL_Map sql;

	@GetMapping("serh") // 계정 찾기 이동
	public String logion_Serch() { return  "/Login_more/login_serh"; }

	@PostMapping("id_ser") // 아이디 찾기 트리거
	public String IDserh(@RequestParam String u_nm, @RequestParam String u_addr, Model model) {
		int ser = 0; // 아이디 찾기 확인 키
		String id = sql.serch_id(u_nm, u_addr); // 찾은 아이디 값 저장
		if(id != null) { ser = 1; model.addAttribute("ser_id", ser); model.addAttribute("serh_id", id); }
		else { ser = 0; model.addAttribute("ser_id", ser); }
			return "/Login_more/login_serh"; }

	@PostMapping("pw_ser") // 비밀번호 찾기 트리거
	public String PWserh(@RequestParam String u_id, @RequestParam String u_nm,
						 @RequestParam String u_addr, Model model) {
		int ser = 0; // 비밀번호 찾기 확인 키
		String pw = sql.serch_pw(u_id ,u_nm, u_addr); // 찾은 비밀번호 값 저장
		if(pw != null) { ser = 1; model.addAttribute("ser_pw", ser); model.addAttribute("serh_pw", pw); }
		else { ser = 0; model.addAttribute("ser_pw", ser); }
		return "/Login_more/login_serh"; } }