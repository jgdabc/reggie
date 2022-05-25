package com.jgdabc.service;

import com.jgdabc.pojo.Brand;
import com.jgdabc.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService01 {
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteByIds( int[] ids);
    PageBean<Brand>selectByPage(int currentPage,int pageSize);
    PageBean<Brand>selectByPageandCondition(int currentPage,int pageSize,Brand brand);



}
