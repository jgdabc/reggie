package com.jgdabc.web.oldservlet;

import com.alibaba.fastjson.JSON;
import com.jgdabc.pojo.Brand;
import com.jgdabc.service.BrandService;
import com.jgdabc.service.BrandService01;
import com.jgdabc.service.impl.BrandServiceImpl;



import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;



@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService01 brandService01 = new BrandServiceImpl();

    BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandService01.selectAll();
//        转为json
        String jsonString = JSON.toJSONString(brands);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);

//        List<Brand> brands = service.selectAll();
//        System.out.println(brands);
////        存入request区域当中
//        request.setAttribute("brands",brands);
//
////        转发到brand.jsp
//        request.getRequestDispatcher("/brand.jsp").forward(request,response);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request,response);
    }
}
