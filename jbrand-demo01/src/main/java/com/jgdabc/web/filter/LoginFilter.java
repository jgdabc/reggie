package com.jgdabc.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*.jsp")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        1判断对应的session是否有user
        //判断访问的资源路径是否和登录注册相关
        String[] urls = {"/video/","index.html","index.jsp","login.jsp","/imgs/","/css/","loginServlet","register.jsp","/registerServlet","/checkCodeServlet"};

        HttpServletRequest req = (HttpServletRequest) request;
        String url = req.getRequestURI().toString();

        for(String u:urls)
        {
            if(url.contains(u)){
                chain.doFilter(request,response);
                return;

            }
        }
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
//        判断user是否为null
        if(user!=null)
        {
            chain.doFilter(request, response);
        }else
        {
//
            req.setAttribute("login_msg","您尚未登录");
            req.getRequestDispatcher("/login.jsp").forward(req,response);
        }


    }
}
