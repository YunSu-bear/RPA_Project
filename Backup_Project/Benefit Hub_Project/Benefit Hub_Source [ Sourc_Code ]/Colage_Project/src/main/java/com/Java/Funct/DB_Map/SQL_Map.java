package com.Java.Funct.DB_Map;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Java.Funct.dto_Util.dto_posts;
import com.Java.Funct.dto_Util.dto_users;

@Mapper
public interface SQL_Map {
	// Select
	@Select("SELECT * FROM users where u_id = 'admin' and u_pw = 'admin'") // 어드민 유저 가져오기
	public List<dto_users> admin_role();

	@Select("SELECT * FROM users where u_id = #{u_id}") // 유저 정보 가져오기
	public List<dto_users> mypage_data(String u_id);

	@Select("SELECT u_id FROM users where u_id = #{u_id}") // 유저 아이디 비교
	public String chking_id(String u_id);

	@Select("SELECT u_id FROM users where u_nm = #{u_nm} and u_addr = #{u_addr}") // 유저 아이디 찾기
	public String serch_id(String u_nm, String u_addr);

	@Select("SELECT u_pw FROM users where u_id = #{u_id} and u_nm = #{u_nm} and u_addr = #{u_addr}") // 유저 비밀번호 찾기
	public String serch_pw(String u_id, String u_nm, String u_addr);

	@Select("SELECT * FROM users;") // 유저 가져오기
	public List<dto_users> selectData();

	@Select("SELECT * FROM posts;") // 게시글 가져오기
	public List<dto_posts> selectPost();

	@Select("SELECT * FROM posts where post_num = #{post_num}") // 게시글 번호로 찾기
	public List<dto_posts> selectPost_num(String post_num);

	@Select("SELECT * FROM users u_id where u_num = #{u_num}") // 유저 번호로 찾기
	public List<dto_users> selectuser_num(String u_num);

	// Insert
	@Insert("insert into users(u_id, u_pw, u_nm, u_addr, u_sec_n1, u_sec_n2) values(#{u_id}, #{u_pw}, #{u_nm}, #{u_addr}, #{u_sec_n1}, #{u_sec_n2})") // 유저 적재
	public void insertData(String u_id, String u_pw, String u_nm,
	                       String u_addr, String u_sec_n1, String u_sec_n2);
	@Insert("INSERT INTO posts (title, chat, post_user) VALUES (#{title}, #{chat}, #{post_user})") // 게시글 적재
	public void insertPost(String title, String chat, String post_user);

	// Update
	@Update("UPDATE USERS SET u_addr =  #{u_addr} where u_id = #{u_id}") // 유저 주소 변경
	public void updat_addr(String u_addr, String u_id);

	@Update("UPDATE USERS SET u_pw =  #{n_pw} where u_pw = #{u_pw}") // 유저 비밀번호 변경
	public void updat_pw(String n_pw, String u_pw);

	@Update("UPDATE USERS SET u_id = #{u_id}, u_pw = #{u_pw}, u_nm = #{u_nm}, u_addr = #{u_addr}, u_sec_n1 = #{u_sec_n1}, u_sec_n2 = #{u_sec_n2} WHERE u_num = #{u_num}")
	public void updat_user(String u_num, String u_id, String u_pw, String u_nm,
            			   String u_addr, String u_sec_n1, String u_sec_n2);
	@Update("UPDATE POSTS SET title = #{title}, chat = #{chat} WHERE post_num = #{post_num}") // 게시글 내용 변경
	public void updat_post(String post_num, String title, String chat);

	// Delete
	@Delete("DELETE FROM posts WHERE post_num = #{post_num}") // 게시글 번호로 삭제
	public void del_post(String post_num);

	@Delete("DELETE FROM users WHERE u_num = #{u_num}") // 유저 번호로 삭제
	public void del_user(String u_num);

	@Delete("DELETE FROM users WHERE u_id = #{u_id}") // 유저 아이디로 삭제
	public void del_user_id(String u_id); }
