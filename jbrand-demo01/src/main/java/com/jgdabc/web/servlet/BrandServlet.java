package com.jgdabc.web.servlet;

import com.alibaba.fastjson.JSON;
import com.jgdabc.pojo.Brand;
import com.jgdabc.pojo.PageBean;

import com.jgdabc.service.BrandService01;
import com.jgdabc.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")

public class BrandServlet extends BaseServlet {
    private BrandService01 brandService01 = new BrandServiceImpl();


    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       System.out.println("brand selectAll");
        List<Brand> brands = brandService01.selectAll();
//        转为json
        String jsonString = JSON.toJSONString(brands);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);


    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();
//        转为对应的brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        String brandName = brand.getBrandName();
        Brand brand1 = brandService01.selectByBrandName(brandName);
        if(brand1==null)
        {
            brandService01.add(brand);
            resp.getWriter().write("success");
        }
        else
        {
            if (brand1.getBrandName().equals(brand.getBrandName())) {
                resp.getWriter().write("failed");
            }
        }

//        brandService01.add(brand);
//        resp.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();
//        转为对应的brand对象
        int[] ids = JSON.parseObject(params, int[].class);
        brandService01.deleteByIds(ids);
        resp.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        转为json
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        PageBean<Brand> pageBean = brandService01.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(pageBean);


        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);


    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        转为json
        String _currentPage = req.getParameter("currentPage");

        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        System.out.println(currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        System.out.println(pageSize);
        BufferedReader br = req.getReader();
        String params = br.readLine();
//        转为对应的brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println(brand);


        PageBean<Brand> pageBean = brandService01.selectByPageandCondition(currentPage, pageSize, brand);
        String jsonString = JSON.toJSONString(pageBean);


        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);


    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();
        System.out.println("我是" + params);
        int id = Integer.parseInt(params);
//        System.out.println(id);
        brandService01.delete(id);


        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write("success");

    }

    public void selectbyid(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();
        System.out.println("我是" + params);
        int id = Integer.parseInt(params);
//        System.out.println(id);
        Brand brand = brandService01.selectById(id);
        System.out.println(brand);
        String s = JSON.toJSONString(brand);


        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(s);

    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        String params = br.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        brandService01.update(brand);

        System.out.println("我是"+brand);
//        System.out.println("我是" + params);


//        System.out.println(brand);
//        String s = JSON.toJSONString(brand);


        resp.setContentType("text/json;charset=utf-8");
//        resp.getWriter().write(s);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write("success");
    }
}
