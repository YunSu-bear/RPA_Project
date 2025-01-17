package com.Java.Funct.Web_Control;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Java.Funct.DB_Map.SQL_Map;
import com.Java.Funct.dto_Util.dto_users;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("Mypage")
public class Web_Control_Mypage {
	@Autowired
	SQL_Map sql;

	@GetMapping("main") // 마이 페이지 이동
	public String Mypage(Model model, Principal principal) {
		String user_info = principal.getName();
	    List<dto_users> mydata = sql.mypage_data(user_info); // 현재 로그인 중인 계정명을 DB를 통해 값 가져오기
	    model.addAttribute("mydata", mydata); // 유저 인증
		model.addAttribute("user", user_info); // 데이터 보여주기
		return "Mypage/Mypage"; }

	@GetMapping("edit_pw") // 마이 페이지 - 비밀번호 변경
	public String Mypage_pw(Model model, Principal principal) {
		String user_info = principal.getName();
		model.addAttribute("user", user_info);
		return "Mypage/Mypage_pw"; }

	@PostMapping("re_addr_action") // 유저 페이지 주소 수정
	public String re_addr(@RequestParam String u_addr,Principal principal, Model model) {
		String u_id =principal.getName();
		String user_info = u_id;
		sql.updat_addr(u_addr, u_id);
	    sql.mypage_data(user_info);
	    List<dto_users> mydata = sql.mypage_data(user_info);
	    model.addAttribute("mydata", mydata);
		model.addAttribute("user", user_info);
		System.out.println(user_info + " : 유저가 주소를 수정했습니다");
		return "Mypage/Mypage_suc"; }

	@GetMapping("delete") // 회원 탈퇴
	public String My_del(Principal principal, Model model) {
		String u_id = principal.getName(); // 보안 유저 정보 확인
		sql.del_user_id(u_id); // DB 유저 정보 제거
		int del = 1; // 회원 탈퇴 알림
		model.addAttribute("del", del);
		System.out.println(u_id + " : 유저가 탈퇴하셨습니다");
		SecurityContextHolder.clearContext(); // 보안 정보 초기화
		return "Main"; }

	@PostMapping("re_passwd_act") // 비밀번호 변경 시 로그아웃 [ 세션 및 쿠키 초기화 ]
	public String re_passwd(@RequestParam String u_pw, @RequestParam String n_pw, @RequestParam String n_pw_c,
	                        HttpServletRequest request, HttpServletResponse response) {
	    sql.updat_pw(n_pw, u_pw);
	    HttpSession session = request.getSession(false); // 세션 불러오기
	    if (session != null) { session.invalidate(); } // 세션 초기화
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) { // 쿠키 제거
	    	for (Cookie cookie : cookies) {
	    		if ("u_info".equals(cookie.getName())) {
	    			cookie.setMaxAge(0);
	    			response.addCookie(cookie); break; } } }
	     return "/login"; } } // 로그아웃
