package com.jgdabc.web.servlet;

import com.jgdabc.mapper.UserMapper;
import com.jgdabc.pojo.User;
import com.jgdabc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");


        //2. 调用MyBatis完成查询
        //2.1 获取SqlSessionFactory对象
       /* String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.2 获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3 获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //2.4 调用方法
        User user = userMapper.select(username, password);
        //2.5 释放资源
        sqlSession.close();


        //获取字符输出流，并设置content type
        response.setContentType("text/html;charset=utf-8");
//        PrintWriter writer = response.getWriter();
        //3. 判断user释放为null
        if(user != null){
            // 登陆成功
//            writer.write("登陆成功");
            if("1".equals(remember))
            {
                Cookie c_username = new Cookie("username", username);
                Cookie c_password = new Cookie("password", password);
                //设置存活时间
                c_username.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);
                response.addCookie(c_username);
                response.addCookie(c_password);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            String contextPath = request.getContextPath();
            response.sendRedirect("selectAllServlet");
        }else {
//            //登录失败

            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
//            JFrame jFrame = new JFrame();
//            int result = JOptionPane.showConfirmDialog(jFrame,"用户不存在，请注册");
//            if (result==0)
//            {
//                response.sendRedirect("register.html");
//            }
//            else
//            {
//                response.sendRedirect("login.html");
//            }
            // 登陆失败
//            writer.write("登陆失败");
//            response.setContentType("text/html;charset=utf-8");
////            PrintWriter out = response.getWriter();
//            out.println ("<script language=javascript>alert('密码错误');window.location='login.jsp'</script>");
//            JOptionPane.showMessageDialog(null, "用户不存在，请注册");




//            String contextPath = request.getContextPath();
//////        简化方式完成重定向
////
//            response.sendRedirect(contextPath+"/register.html");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}