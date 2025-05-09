package com.example.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.news.dao.NewsDAO;

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
	
	
	
	

}
