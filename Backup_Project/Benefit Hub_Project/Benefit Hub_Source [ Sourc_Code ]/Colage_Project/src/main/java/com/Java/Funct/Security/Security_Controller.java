package com.Java.Funct.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.Java.Funct.DB_Map.SQL_Map;
import com.Java.Funct.dto_Util.dto_users;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@Controller
public class Security_Controller {
	@Autowired // 패키지 연결
	SQL_Map sql; // DB 연결
	dto_users dto; // DTO 연결
	Security_cfg cfg; // 보안 설정 연결
    @PostMapping("/login_act") // 로그인 시 세션 및 쿠키 연결
    public String getUserInfo(HttpServletRequest reques, HttpServletResponse response, Model model) {
    	System.out.println("유저 정보 확인중입니다"); // 유저 정보 확인
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String user_info = authentication.getName(); // 유저 로그인 확인
    	System.out.println(user_info + " : 정보 인증되었습니다");
		Cookie cok = new Cookie("u_info", user_info); // 쿠키 생성 및 연결
		cok.setMaxAge(3600); cok.setPath("/");
		System.out.println(user_info + " : 유저에게 쿠키 지급되었습니다");
		response.addCookie(cok);
		HttpSession session = reques.getSession(); // 세션 생성 및 연결
		System.out.println(user_info + " : 세션이 만들어졌습니다");
		session.setAttribute("user", user_info);
		model.addAttribute("user", user_info);
		System.out.println(user_info + " : 로그인 성공!"); // 로그인 성공 확인
		return "/Main"; }
}
