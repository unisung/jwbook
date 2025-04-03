package ch12;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api") // "/api~경로는 RESTful기능
public class RestConfig extends Application {
	public Map<String, Object> getProperties(){
		//맵객체 생성
		Map<String, Object> properties = new HashMap<String,Object>();
		//package정보 등록
		properties.put("jersey.config.server.provider.packages", "ch12");
		//맵 객체 리턴
		return properties;
	}
}
