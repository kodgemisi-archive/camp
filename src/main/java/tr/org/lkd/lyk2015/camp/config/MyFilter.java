package tr.org.lkd.lyk2015.camp.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class MyFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding( "utf-8" );
	    response.setCharacterEncoding( "utf-8" );
	    response.setContentType("text/html,charset=UTF-8");
	    
	    chain.doFilter( request, response );
		

	    response.setCharacterEncoding( "utf-8" );
	    response.setContentType("text/html,charset=UTF-8");
	    
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
