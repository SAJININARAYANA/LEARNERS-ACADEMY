package com.bezkoder.spring.datajpa;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilterConfig implements Filter {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "*");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Headers", "*");
    response.setHeader("Access-Control-Expose-Headers", "*");

    chain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig filterConfig) {
    // No implementation
  }

  @Override
  public void destroy() {
    // No implementation
  }
}
