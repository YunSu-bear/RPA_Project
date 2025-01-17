package com.Java.Funct.Web_Control;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Java.Funct.DB_Map.SQL_Map;
import com.Java.Funct.dto_Util.dto_posts;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Community")
public class Web_Control_Community {
	@Autowired
	SQL_Map sql;

	@GetMapping("List") // 커뮤니티 페이지 [ 회원자가 아닐 가능성을 생각해 쿠키 및 세션 직접 가져오기 ]
	public String Community_List(HttpServletRequest request, Model model) {
		List<dto_posts> post = sql.selectPost();
		model.addAttribute("posts",post);
		Cookie[] cookies = request.getCookies();
		String user_info = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("u_info")) { user_info = cookie.getValue(); break; } } }
		HttpSession session = request.getSession();
		String session_user_info = (String) session.getAttribute("user");
		if (user_info != null && user_info.equals(session_user_info)) { model.addAttribute("user", user_info); }
	    return "Community/Community_List"; }

	@GetMapping("Noice") // 공지사항 페이지 이동
	public String Community_noice(Model model, Principal principal) {
		String user_info = principal.getName();
		model.addAttribute("user", user_info);
		return "Community/Noice"; }

	@GetMapping("Writing") // 글쓰기 페이지 이동
	public String Community_Writing(Model model, Principal principal) {
		String user_info = principal.getName();
		model.addAttribute("user", user_info);
		return "Community/Writing"; }

	@PostMapping("writing_action") // 글쓰기 DB 액션
	public String Community_wri_act(@RequestParam String title, @RequestParam String chat, Model model, Principal principal) {
		String user_info = principal.getName();
		model.addAttribute("user", user_info);
    	sql.insertPost(title, chat ,user_info); // 글 쓴 정보를 db에 적재
    return "Community/Community_List"; }

	@GetMapping("Post") // 게시글 보기
	public String Community_Post(@RequestParam String num, Model model, Principal principal) {
		String user_info = principal.getName(); // 유저 인증
	    List<dto_posts> p_num = sql.selectPost_num(num); // 저장된 포스트중 p_num가 일치된 포스트 정보
	    model.addAttribute("user", user_info); // 클라이언트에게 정보 보여주기
		model.addAttribute("p_num_poster",p_num);
		return "Community/Post"; }
}
