package com.Java.Funct.dto_Util;

public class dto_posts_bulider {
	private String post_num, title, chat, post_user,time;

	public dto_posts_bulider setter(String post_num, String title, String chat, String post_user, String time) {
		this.post_num = post_num;
		this.title = title;
		this.chat = chat;
		this.post_user = post_user;
		this.time = time;
		return this; }

	public dto_posts dtobul() { return new dto_posts(title, chat, post_user, time, post_num); }
}