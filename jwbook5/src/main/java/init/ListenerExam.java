package init;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class ListenerExam implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener {
	//생성자
    public ListenerExam() {}

    //ServletContext에 속성 추가
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	scae.getServletContext().log("SevletContext 속성 추가: "+scae.getValue());
    }
    //ServletContext
    public void attributeRemoved(ServletContextAttributeEvent scae)  { }
    //ServletContext 종료 이벤트 발생시 실행
    public void contextDestroyed(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 종료됨!!!!");
    }
    //ServletContext
    public void attributeReplaced(ServletContextAttributeEvent scae)  { }
    //ServletContext 실행 이벤트 발생시 실행
    public void contextInitialized(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 시작됨!!!!");
    }

    //session
    //세션
    public void sessionCreated(HttpSessionEvent se)  {
    	se.getSession().getServletContext().log("Session 생성됨:"+se.getSession().getId());
    }
    public void sessionDestroyed(HttpSessionEvent se)  { }
    //세션에 속성추가
    public void attributeAdded(HttpSessionBindingEvent se)  { 
    	se.getSession().getServletContext().log("Session 속성 추가:"+se.getValue());
    }
    public void attributeRemoved(HttpSessionBindingEvent se)  { }
    public void attributeReplaced(HttpSessionBindingEvent se)  { }
	
}
