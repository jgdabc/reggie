package com.jgdabc.web;

import com.jgdabc.pojo.Brand;
import com.jgdabc.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       //接收id
        String id = request.getParameter("id");
        //调用service查询
        Brand brand = service.selectById(Integer.parseInt(id));
        //存储到request域当中
        request.setAttribute("brand",brand);
        //转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
