package com.example.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.news.dao.NewsDAO;
import com.example.news.model.News;

@RestController
@RequestMapping("/api/news")
public class NewsApiController {
	final NewsDAO dao;

	@Autowired //생성자 주입
	public NewsApiController(NewsDAO dao) {
		this.dao = dao;
	}
	
	//뉴스 등록
	@PostMapping
	public String addNews(@RequestBody News news) {//클라이언트로부터 JSON데이타를 받음
		try {
			 dao.addNews(news);
		}catch(Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 등록 실패!";
		}
		return "News API: 뉴스 등록됨!!";
	}
	
	//뉴스 목록
	@GetMapping /* localhost:8080/api/news/ */
	public List<News> getNewsList(){
		List<News> newsList = null;
		try {
			newsList = dao.getAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}
	
	//뉴스 상세 정보
	@GetMapping("{aid}") /* localhost:8080/api/news/8 */
	public News getNews(@PathVariable("aid") int aid) {
		News news = null;
		try {
			 news = dao.getNews(aid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return news;
	}
	
	//뉴스 수정
	@PutMapping
	public String updateNews(@RequestBody News news) {
		try {
			 dao.updateNews(news);
		}catch(Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 수정 실패!";
		}
		return "News API: 뉴스 수정 성공!";
	}

	 /*
	  localhost:8080/api/news <--  GET 리스트
	  localhost:8080/api/news <-- POST, - 등록
	  localhost:8080/api/news <-- PUT, - 등록/수정
	  localhost:8080/api/news <-- PATCH - 수정
	  localhost:8080/api/news/18 <--- DELETE -삭제
	  localhost:8080/api/news/18 <--- GET - 상세보기
	  */
	
	//뉴스 수정
	 @PatchMapping
	public String updateNews1(@RequestBody News news) {
			try {
				 dao.updateNews(news);
			}catch(Exception e) {
				e.printStackTrace();
				return "News API: 뉴스 수정 실패!";
			}
			return "News API: 뉴스 수정 성공!";
		}
	 
	 
	 //뉴스 삭제
	 @DeleteMapping("{aid}")
	 public String delNews(@PathVariable("aid") int aid) {
		 try {
			  dao.deleteNews(aid);
		 }catch(Exception e) {
			 e.printStackTrace();
			 return "News API: 뉴스 삭제 실패!";
		 }
		 return "News API: 뉴스 삭제 성공!";
	 }
}
