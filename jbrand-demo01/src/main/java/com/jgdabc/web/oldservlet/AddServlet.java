package com.jgdabc.web.oldservlet;

import com.alibaba.fastjson.JSON;
import com.jgdabc.pojo.Brand;
import com.jgdabc.service.BrandService;
import com.jgdabc.service.BrandService01;
import com.jgdabc.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService service = new BrandService();
    private BrandService01 brandService01 = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        //接收表单提交的数据，封装为brand对象
//        接收品牌数据

        BufferedReader br = request.getReader();
        String params = br.readLine();
//        转为对应的brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService01.add(brand);
        response.getWriter().write("success");
//        调用方法添加


//        处理乱码问题
//        request.setCharacterEncoding("utf-8");
//
//
//        String brandName = request.getParameter("brandName");
//        String companyName = request.getParameter("companyName");
//        String ordered = request.getParameter("ordered");
//        String description = request.getParameter("description");
//        String status = request.getParameter("status");
//        System.out.println("brandName"+brandName);
//        //封装为一个Brand对象
//        Brand brand = new Brand();
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
//
//        brand.setOrdered(Integer.parseInt(ordered));
//        brand.setDescription(description);
//        brand.setStatus(Integer.parseInt(status));
//        service.add(brand);
////        响应成功的标识
//        response.getWriter().write("success");
////        转发到查询所有Servlet
//        //request.getRequestDispatcher("/selectAllServlet").forward(request, response);
//        response.sendRedirect("selectAllServlet");
//        System.out.println("request");
//

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("post");
       doGet(request, response);

    }
}
