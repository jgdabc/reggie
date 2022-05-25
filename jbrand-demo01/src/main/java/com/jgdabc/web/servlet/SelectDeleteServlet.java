package com.jgdabc.web.servlet;

import com.jgdabc.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectDeleteServlet")
public class SelectDeleteServlet extends HttpServlet {
    BrandService service = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BrandService brandService = new BrandService();
        String id = request.getParameter("id");
        Integer id_1 = Integer.parseInt(id);
        brandService.delete(id_1);

//        request.getRequestDispatcher("/brand.jsp").forward(request,response);
        response.sendRedirect("selectAllServlet");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
