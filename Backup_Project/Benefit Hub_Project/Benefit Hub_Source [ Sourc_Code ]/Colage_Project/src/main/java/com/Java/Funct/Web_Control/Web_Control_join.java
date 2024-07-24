package com.Java.Funct.Web_Control;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Java.Funct.DB_Map.SQL_Map;

@Controller
@RequestMapping("/Join")
public class Web_Control_join {
	@Autowired
	SQL_Map sql;

	@GetMapping("join") // 로그인 페이지 이동
	public String Login_Wel() { return "/Join/join"; }

	@PostMapping("dup_chk") // 아이디 중복 확인 트리거
	public String user_id_chk (@RequestParam(name="username") String u_id, Model model, Principal principal) {
		System.out.println("체크중입니다");
		int chk = 0; // 아이디 중복 확인 키
		String chked = sql.chking_id(u_id); // 아이디 중복 확인
		if(chked != null && chked.equals(u_id)) { chk = 1; model.addAttribute("dup", chk); }
		else { chk = 0; model.addAttribute("dup", chk); }
		return "/Join/join_chk"; }

	@PostMapping("join_up") // 회원 정보 DB 적재
	public String users_data(@RequestParam(name="username") String u_id,
							 @RequestParam(name="password") String u_pw,
							 @RequestParam String u_nm, @RequestParam String u_addr,
							 @RequestParam String u_sec_n1, @RequestParam String u_sec_n2) {
		sql.insertData(u_id, u_pw, u_nm, u_addr, u_sec_n1, u_sec_n2); // DB 적재
		return "/Join/login_suc"; }

	@PostMapping("join_suc") // 회원가입 성공 시 이동
	public String join_suc() { return "/Join/join_suc"; } }
