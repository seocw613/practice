package com.ezen.myProject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
	private String id;
	private String pw;
	private String age;
	private String name;
	private String email;
	private String home;
}
