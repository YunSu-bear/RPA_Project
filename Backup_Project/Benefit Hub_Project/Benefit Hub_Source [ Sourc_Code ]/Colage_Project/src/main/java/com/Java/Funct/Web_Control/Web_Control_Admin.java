package com.Java.Funct.Web_Control;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Java.Funct.DB_Map.SQL_Map;
import com.Java.Funct.dto_Util.dto_posts;
import com.Java.Funct.dto_Util.dto_users;

@Controller
public class Web_Control_Admin {
	@Autowired // db 연결
	SQL_Map sql;

	@GetMapping("admin_Userpage") // 유저 관리 페이지
	public String ad_user(Model model, Principal principal) {
		String user_info = principal.getName();
	    List<dto_users> Users_li = sql.selectData();
	    model.addAttribute("user", user_info);
		model.addAttribute("A", Users_li);
	    return "admin_Userpage"; }

	@GetMapping("admin_Postpage") // 게시글 관리 페이지
	public String ad_post(Model model, Principal principal) {
		String user_info = principal.getName();
		List<dto_posts> Posts_li = sql.selectPost();
		model.addAttribute("user", user_info);
		model.addAttribute("P", Posts_li);
	    return "admin_Postpage"; }

	// -------------------------------------- 유저 트리거 ----------------------------------------------

	@PostMapping("edit_action_u") // 유저 수정 트리거
	public String user_edit(@RequestParam(name="edit_user") String u_num, Model model,Principal principal) {
		String user_info = principal.getName();
		List<dto_users> edit_li = sql.selectuser_num(u_num);
		System.out.println(u_num);
		model.addAttribute("user", user_info);
		model.addAttribute("sel_user", edit_li);
		return "/edit_action"; }

	@PostMapping("edit_user") // 유저 수정 완료 트리거
	public String user_edit_suc(@RequestParam(name="username") String u_id, @RequestParam(name="password") String u_pw,
							 	@RequestParam String u_nm, @RequestParam String u_addr,
							 	@RequestParam String u_sec_n1, @RequestParam String u_sec_n2,@RequestParam String u_num,
							 	Model model, Principal principal) {
		String user_info = principal.getName();
		sql.updat_user(u_num, u_id, u_pw, u_nm, u_addr, u_sec_n1, u_sec_n2); // 유저 정보 업데이트
		model.addAttribute("user", user_info);
		return "/admin_UserPage"; }

	@PostMapping("delete_action_u") // 유저 삭제 트리거
	public String user_del(@RequestParam(name="B") String u_num) {
		sql.del_user(u_num); // 유저 번호로 정보 삭제
		return "/admin_Userpage"; }

	// -------------------------------------- 게시글 트리거 ----------------------------------------------

	@PostMapping("edit_action_p") // 게시글 수정 트리거
	public String post_edit(@RequestParam(name="A") String post_num, Principal principal,Model model) {
		String user_info = principal.getName();
		List<dto_posts> edit_li = sql.selectPost_num(post_num); // 게시글 번호로 정보 가져오기
		model.addAttribute("sel_post", edit_li); // 게시글 선택 값
		model.addAttribute("user", user_info);
		return "/Community/Writing [ admin ]"; }

	@PostMapping("edit_writing_action") // 게시글 수정 완료 트리거
	public String post_edit_suc(@RequestParam String post_num, @RequestParam String title, @RequestParam String chat
								,Model model, Principal principal) {
		String user_info = principal.getName();
		sql.updat_post(post_num, title, chat); // 게시글 수정
		model.addAttribute("user", user_info);
		return "/admin_PostPage"; }

	@PostMapping("delete_action_p") // 게시글 삭제 트리거
	public String post_del(@RequestParam(name="A") String post_num) {
		sql.del_post(post_num); // 게시글 번호로 삭제
		return "/admin_Postpage"; } }

