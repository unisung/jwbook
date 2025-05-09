package com.example.news.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class News {
	//필드
	private int aid; //뉴스id
	private String title;//뉴스제목
	private String img;//이미지 '파일명'
	private String date; //등록일시
	private String content; //뉴스내용
}
