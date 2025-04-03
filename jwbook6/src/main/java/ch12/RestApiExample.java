package ch12;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

//POJO(Plain Old Java Object)
@Path("/test") // /api/test~~
public class RestApiExample {
  @GET //get방식 요청 처리   // http:localhost:8080/api/test
  @Produces(MediaType.TEXT_PLAIN) //HTML이 아닌 일반 문자열형태로 응답
  public String sayHello() {
	  return "Hello API Service";
  }
  
  @POST //post방식 요청 처리
  public String sayHello(@QueryParam("msg") String msg) { // http:localhost:8080/api/test?msg=메세지
	  return msg + " API Service";
  }
}
