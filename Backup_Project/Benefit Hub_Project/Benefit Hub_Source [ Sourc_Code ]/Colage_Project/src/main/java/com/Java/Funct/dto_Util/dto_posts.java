	package com.Java.Funct.dto_Util;

public class dto_posts {
	private String post_num, title, chat, post_user, time;

	public dto_posts(String post_num, String title, String chat, String post_user, String time) {
		this.post_num = post_num;
		this.title = title;
		this.chat = chat;
		this.post_user = post_user;
		this.time = time; }

	public String get_post_num() { return post_num; }
	public String get_title() { return title; }
	public String get_chat() { return chat; }
	public String get_post_user() { return post_user; }
	public String get_time() { return time; }
}
