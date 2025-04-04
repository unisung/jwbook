package ch12;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api") //REST api로 작동을 위한 설정
public class RestConfig extends Application {
	public Map<String, Object> geProperties(){
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("jersey.config.server.provider.packages", "ch12");
		return properties;
	}	
}
