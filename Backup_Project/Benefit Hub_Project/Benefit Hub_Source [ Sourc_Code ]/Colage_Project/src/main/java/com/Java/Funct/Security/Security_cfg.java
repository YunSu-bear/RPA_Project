package com.Java.Funct.Security;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Java.Funct.DB_Map.SQL_Map;
import com.Java.Funct.dto_Util.dto_users;

@Configuration
@EnableWebSecurity
@Controller
public class Security_cfg {
	InMemoryUserDetailsManager list = new InMemoryUserDetailsManager();
	@Autowired
		SQL_Map sql;
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/css/**","/images/**","/js/**","../images/**","../css/**", // 디자인 및 기능
            				 "/user_sec_insert", // 유저 시큐리티 적재
            				 "/Login_more/**", "/Join/**",// 정보 찾기
            				 "/", "/Main", "Community/List", // 기본 페이지
            				 "/Mypage/**", // 마이 페이지
            				 "/welcome", "/login","/login_w") // 로그인 관련 페이지
               				 .permitAll() // 페이지에 대한 접근 허용
            .anyRequest().authenticated()) // 나머지 모든 요청은 인증이 필요

        	.formLogin(login -> login // 로그인에 대한 설정
        			.loginPage("/welcome") // 로그인 페이지
        			.loginProcessingUrl("/login") // 로그인 인증 트리거
        			.successForwardUrl("/login_act")) // 로그인 성공 트리거

        	.logout(logout -> logout // 로그아웃에 대한 설정
        			.logoutUrl("/logout") // 로그아웃 트리거
        			.logoutSuccessUrl("/Main") // 로그아웃 성공 트리거
        			.invalidateHttpSession(true) // 세션 초기화
        			.deleteCookies("u_info")) // 쿠키 초기화

        .csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화
        return http.build(); }

	@Bean
	public UserDetailsService DB_add_config() { // db에 저장된 유저 불러오기
		System.out.println("유저 정보 불러오는중..");
		Iterator<dto_users> dt_Datas = sql.selectData().iterator();
		while(dt_Datas.hasNext()) {
			dto_users Datas_u = dt_Datas.next();
			if(!Datas_u.getU_id().equals("admin") || !Datas_u.getU_pw().equals("admin")) {
				UserDetails DB_user = User.withUsername(Datas_u.getU_id())
						.password("{noop}"+Datas_u.getU_pw())
						.roles("USER")
						.build();
				list.createUser(DB_user); } }
		System.out.println("관리자 정보 불러오는중..");
		Iterator<dto_users> admin_Datas = sql.admin_role().iterator();
		while(admin_Datas.hasNext()) {
			dto_users Datas_a = admin_Datas.next();
			UserDetails admin = User.withUsername(Datas_a.getU_id())
					.password("{noop}" + Datas_a.getU_pw())
					.roles("ADMIN") // 관리자 권한 부여
					.build();
			list.createUser(admin); }
		System.out.println("성공적으로 불러왔습니다");
		return list; }

	@RequestMapping("/user_sec_insert") // 실시간 로그인
	public String DB_join_insert() {
		System.out.println("유저 정보를 적재중입니다..");
		Iterator<dto_users> n_Datalist = sql.selectData().iterator();
		while(true) {
			dto_users n_Datas = n_Datalist.next();
			if(n_Datalist.hasNext() != true) {
				UserDetails DB_user = User.withUsername(n_Datas.getU_id())
						.password("{noop}"+n_Datas.getU_pw())
						.roles("USER")
						.build();
				list.createUser(DB_user);
				break; } }
		System.out.println("성공적으로 적재되었습니다");
		return "/Main"; }
}


