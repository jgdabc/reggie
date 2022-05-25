package com.jgdabc.web.servlet;

//替换HttpServlet,根据请求的最后一段路径来进行方法分发

import com.jgdabc.service.BrandService01;
import com.jgdabc.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

//    根据请求的最后一段路径来进行方法分发
//    获取请求路径


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);

//        获取请求路径
        String uri = req.getRequestURI();
        System.out.println(uri);
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);
        System.out.println(methodName);
//        执行方法
//        获取BrandServlet/UserServlet 字节码对象
//        获取方法的method对象
//        this 代表谁调用我我代表谁（看service被谁调用）
        System.out.println(this);
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
//        执行方法
    }
}

