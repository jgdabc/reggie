package com.jgdabc.mapper;

import com.jgdabc.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    List<Brand>selectAll();
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);
    @Select("select * from tb_brand where id = #{id}")
    Brand selectById(int id);
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);
    @Delete("delete  from tb_brand where id = #{id}")
    void delete(int id);

}
