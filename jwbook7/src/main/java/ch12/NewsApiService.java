package ch12;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;//ws.rs패키지
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch12.dao.NewsDAO;
import ch12.model.News;

@Path("/news") // localhost:8080/jwbook7/api/news
public class NewsApiService {
	NewsDAO dao;

	public NewsApiService() {
		dao = new NewsDAO(); // 주입
	}

	// 뉴스 목록
	@GET // GET localhost:8080/jwbook7/api/news
	@Produces(MediaType.APPLICATION_JSON)
	public List<News> getNewsList() {
		List<News> newsList = null;
		try {
			newsList = dao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}

	// 뉴스 등록
	@POST  //  POST localhost:8080/jwbook7/api/news
	@Consumes(MediaType.APPLICATION_JSON)//클라이언트로 부터 넘어온 데이타가 JSON타입
	public  String addNews(News news) {
		try {
			//NewsDAO의 addNews() 메소드 호출하여 DB에 저장
			  dao.addNews(news);
		} catch (Exception e) {
			e.printStackTrace();
			return "News API: 뉴스 등록 실패!!";
		}
		return "News API: 뉴스 등록 성공!!";
	}

	// 뉴스 상세 정보
	@GET
	@Path("{aid}") //path variable {변수}
	@Produces(MediaType.APPLICATION_JSON)
	public News getNews(@PathParam("aid") int aid) {
		System.out.println("aid:"+aid);
		News news = null;
		try {
			  news = dao.getNews(aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return news;
	}
}
