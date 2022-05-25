package com.jgdabc.web.servlet;

import com.jgdabc.pojo.User;
import com.jgdabc.service.UserService;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectUserServlet")
public class ServletUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService userService = new UserService();
        boolean flag = userService.register(user);
        System.out.println("来啦！");
        if(flag==false)
        {

            System.out.println("写入");
            response.getWriter().write(""+flag);
//            return;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
