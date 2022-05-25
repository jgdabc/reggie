package com.jgdabc.web.servlet;

import com.jgdabc.pojo.User;
import com.jgdabc.service.UserService;
import com.jgdabc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收用户数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
//        System.out.println(checkCode);
        HttpSession session = request.getSession();
        String checkCodeGen =  (String) session.getAttribute("checkCodeGen");
        System.out.println(checkCodeGen);
        boolean b = checkCodeGen.equalsIgnoreCase(checkCode);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
//        request.setAttribute("register_msg","用户已经存在");
        boolean flag = service.register(user);
//
//        System.out.println(flag);



        if(!b)
        {
            System.out.println("比对失败");
            request.setAttribute("register_msg","验证码错误,请输入验证码");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

//        //封装用户对象
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        boolean flag = service.register(user);
        if (!flag)
        {
//            注册功能，跳转登录界面
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else {
            //注册失败，跳转到注册页面
            request.setAttribute("register_msg","用户已经存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }


        //2. 调用mapper 根据用户名查询用户对象
        //2.1 获取SqlSessionFactory对象
       /* String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.2 获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //2.3 获取Mapper
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//
//        //2.4 调用方法
//        User u = userMapper.selectByUsername(username);
//
//        //3. 判断用户对象释放为null
//        if( u == null){
//            // 用户名不存在，添加用户
//            userMapper.add(user);
//
//            // 提交事务
//            sqlSession.commit();
//            // 释放资源
//            sqlSession.close();
//            response.setContentType("text/html;charset=utf-8");
//            response.getWriter().write("用户注册成功");
//            JOptionPane.showMessageDialog(null, "注册成功。返回登录");
//            String contextPath = request.getContextPath();
//            response.sendRedirect(contextPath+"/login.html");
//        }else {
//            JFrame jFrame = new JFrame();
//            int result = JOptionPane.showConfirmDialog(jFrame,"用户重复，请重新注册");
//            if (result==0)
//            {
//                response.sendRedirect("register.html");
//            }
//            else
//            {
//                response.sendRedirect("login.html");
//            }
////            System.out.println(result);
////            // 用户名存在，给出提示信息
////            response.setContentType("text/html;charset=utf-8");
////            response.getWriter().write("用户名已存在");
////            JOptionPane.showMessageDialog(null, "用户名重复，请重新注册");
////
////            String contentType = response.getContentType();
////            response.sendRedirect("register.html");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}