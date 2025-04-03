package ch11;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter("*.nhn")
public class EncodingFilter extends HttpFilter implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, 
			             FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		if(httpReq.getMethod().equalsIgnoreCase("POST")) {
			request.setCharacterEncoding("utf-8");
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
