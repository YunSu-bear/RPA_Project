package com.Java.Funct.Web_Control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Web_Control {

	@GetMapping("/") // Main 페이지 [ 회원자가 아닐 가능성을 생각해 쿠키 및 세션 직접 가져오기 ]
	public String first(HttpServletRequest request, Model model) {
	    Cookie[] cookies = request.getCookies();
	    String user_info = null;
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("u_info")) { user_info = cookie.getValue(); break; } } }
	    HttpSession session = request.getSession();
	    String session_user_info = (String) session.getAttribute("user");
	    if (user_info != null && user_info.equals(session_user_info)) { model.addAttribute("user", user_info); }
		return "/Main"; }

	@GetMapping("/Main") // Main 페이지 [ 위와 동일 ]
	public String first_chk(HttpServletRequest request, Model model) {
	    Cookie[] cookies = request.getCookies();
	    String user_info = null;
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("u_info")) {
	                user_info = cookie.getValue(); break; } } }
	    HttpSession session = request.getSession();
	    String session_user_info = (String) session.getAttribute("user");
	    if (user_info != null && user_info.equals(session_user_info)) { model.addAttribute("user", user_info); }
		return "/Main"; }

	@GetMapping("welcome") // 로그인 페이지 이동
	public String Login_Wel() { return "/login_w"; } }


