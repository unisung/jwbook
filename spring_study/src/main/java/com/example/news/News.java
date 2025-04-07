package com.example.news;

public class News {
	//필드
	private int aid; //뉴스id
	private String title;//뉴스제목
	private String img;//이미지 '파일명'
	private String date; //등록일시
	private String content; //뉴스내용
	//생성자
	public News() {}
	public News(int aid, String title, String img, String date, String content) {
		this.aid = aid;
		this.title = title;
		this.img = img;
		this.date = date;
		this.content = content;
	}
	//getters/setters
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	//toString
	@Override
	public String toString() {
		return "News [aid=" + aid + ", title=" + title + ", img=" + img + ", date=" + date + ", content=" + content
				+ "]";
	}
	

	
	

}
