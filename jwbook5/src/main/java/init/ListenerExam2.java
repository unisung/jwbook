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
public class ListenerExam2 
        implements ServletContextListener, ServletContextAttributeListener, 
           HttpSessionListener, HttpSessionAttributeListener {

    public ListenerExam2() {}
        
    //ServetContext
    public void contextInitialized(ServletContextEvent sce)  { 
    	sce.getServletContext().log("ServletContext 시작됨!!!!");
    }
    //속성
    public void attributeAdded(ServletContextAttributeEvent scae)  { 
    	scae.getServletContext().log("SevletContext 속성 추가: "+scae.getValue());
    }
    public void attributeRemoved(ServletContextAttributeEvent scae)  { }
    public void attributeReplaced(ServletContextAttributeEvent scae)  {}
    //소멸
    public void contextDestroyed(ServletContextEvent sce)  {
    	sce.getServletContext().log("ServletContext 종료됨!!!!");
    }
    
    //Session
    public void sessionCreated(HttpSessionEvent se)  { 
    	se.getSession().getServletContext().log("Session 생성됨:"+se.getSession().getId());
    }
    //속성
    public void attributeAdded(HttpSessionBindingEvent se)  {
    	se.getSession().getServletContext().log("Session 속성 추가:"+se.getValue());
    }
    public void attributeReplaced(HttpSessionBindingEvent se)  {}
    public void attributeRemoved(HttpSessionBindingEvent se)  { }
    //소멸
    public void sessionDestroyed(HttpSessionEvent se)  {}

	
}
