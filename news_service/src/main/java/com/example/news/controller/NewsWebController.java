package com.example.news.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.news.dao.NewsDAO;
import com.example.news.model.News;

@Controller
@RequestMapping("/news")
public class NewsWebController {
	final NewsDAO dao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    //프로퍼티 파일로 부터 저장 경로 참조
    @Value("${news.imgdir}")
    String fdir;
    
	@Autowired
	public NewsWebController(NewsDAO dao) {
		this.dao = dao;
	}
	
	//뉴스 리스트
	@GetMapping("/list")
	public String listNews(Model m) {
		 List<News> list=null;
		try {
			 list = dao.getAll();
			 m.addAttribute("newsList", list);
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("뉴스 목록 생성 과정에서 문제 발생!");
		}
		return "news/newsList"; /* "/WEB-INF/views/" + "news/newsList" + ".jsp"   */
	}
	
	//뉴스 상세
	@GetMapping("/{aid}") /* /news/{aid} */
	public String getNews(@PathVariable int aid, Model m) {
		try {
			News n = dao.getNews(aid);
			m.addAttribute("news",n);
		}catch(Exception e) {
			e.printStackTrace();
			logger.warn("뉴스를 가져오는 과정에서 문제 발생!");
			m.addAttribute("error","뉴스를 정상적으로 가져오지 못했습니다.");
		}
		return "news/newsView";
	}
	
	
	
	

}
