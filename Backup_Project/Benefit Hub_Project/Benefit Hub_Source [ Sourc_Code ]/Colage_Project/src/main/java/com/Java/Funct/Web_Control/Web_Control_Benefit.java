package com.Java.Funct.Web_Control;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Benefit")
public class Web_Control_Benefit {

	@GetMapping("List") // Benefit 페이지 [ 회원자가 아닐 가능성을 생각해 쿠키 및 세션 직접 가져오기 ]
	public String Benefit_List(HttpServletRequest request, Model model) {
		Cookie[] cookies = request.getCookies();
		String user_info = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("u_info")) { user_info = cookie.getValue(); break; } } }
		HttpSession session = request.getSession();
		String session_user_info = (String) session.getAttribute("user");
		if (user_info != null && user_info.equals(session_user_info)) { model.addAttribute("user", user_info); }
	    return "/Benefit/Benefit_List"; }

	@GetMapping("1") // 1번 카테고리
	public String Benefit_1(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_1"; }

	@GetMapping("2") // 2번 카테고리
	public String Benefit_2(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_2"; }

	@GetMapping("3") // 3번 카테고리
	public String Benefit_3(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_3"; }

	@GetMapping("4") // 4번 카테고리
	public String Benefit_4(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_4"; }

	@GetMapping("5") // 5번 카테고리
	public String Benefit_5(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_5"; }

	@GetMapping("6") // 6번 카테고리
	public String Benefit_6(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_6"; }

	@GetMapping("7") // 7번 카테고리
	public String Benefit_7(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return "/Benefit/Benefit_7"; }

	@GetMapping("8") // 8번 카테고리
	public String Benefit_8(Model model, Principal principal) {
		String user_info = principal.getName(); model.addAttribute("user", user_info);
		return  "/Benefit/Benefit_8"; }
}
