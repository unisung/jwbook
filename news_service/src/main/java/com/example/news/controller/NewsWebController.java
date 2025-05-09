package com.example.news.controller;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/add") /* http://localhost:8080/news/add */
	public String addNews(@ModelAttribute News news, Model m, 
			              @RequestParam("file") MultipartFile file) {
		try {
			 logger.info("fdir:"+fdir);
			 //저장 파일 객체 생성
			 File dest = new File(fdir + "/" + file.getOriginalFilename());
			 
			 logger.info("경로:"+dest.getAbsolutePath()+", 경로:"
			                 +dest.getPath()+",파일명:"+dest.getName()); 
			 //파일저장
			 file.transferTo(dest);
			 
			 //db에 저장
			 news.setImg("/img/"+dest.getName());
			 dao.addNews(news);
		}catch (Exception e) {
			e.printStackTrace();
			logger.warn("뉴스 추가 과정에서 문제 발생!");
			m.addAttribute("error","뉴스가 정상적으로 등록되지 않았습니다.");
		}
		
		return "redirect:/news/list"; /* http://localhost:8080/news/list  */
		
	}
	
	@GetMapping("/update/{aid}")
	public String update(@PathVariable int aid, Model m) {
		News news;
		try {
			news = dao.getNews(aid);
			m.addAttribute("news", news);
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("뉴스 조회 과정에서 문제 발생!");
			m.addAttribute("error","뉴스가 정상적으로 조회되지 않았습니다.");
		}
		return "news/newsForm";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute News news, Model m, 
                     @RequestParam(value = "file", required = false) MultipartFile file) {
		//수정부분 처리 file 전송여부에 따른 수정
		return "redirect:/news/"+news.getAid();
	}
	

}
