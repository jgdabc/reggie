package com.jgdabc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jgdabc.common.R_;
import com.jgdabc.dto.SetmealDto;
import com.jgdabc.entity.Category;
import com.jgdabc.entity.Setmeal;
import com.jgdabc.service.CategoryService;
import com.jgdabc.service.SetMealService;
import com.jgdabc.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//套餐管理
@RestController
@RequestMapping("/setmeal")

public class SetmealController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetMealService setMealService;
    @Autowired
    private SetmealDishService setmealDishService;
    @PostMapping
    public R_<String> save(@RequestBody SetmealDto setmealDto)
    {
        log.info("套餐信息:{}",setmealDto);
       setMealService.saveWithDish(setmealDto);
       return  R_.success("新增套餐成功");
    }
//    套餐分页查询
    @GetMapping("/page")
    public R_<Page> page(int page,int pageSize,String name)
    {

//        构造一个1分页1构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> DtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
//        根据添加name是否查询，如果name为空就不会作为查询条件1，如果不为空就作为查询条件
        queryWrapper.like(name!=null,Setmeal::getName,name);
//        添加一个排序条件，根据更新时间来进行降序排列
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        setMealService.page(pageInfo,queryWrapper);
//        拷贝属性
        BeanUtils.copyProperties(pageInfo,DtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
       List<SetmealDto> list = records.stream().map((item)->
        {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);

            Long categoryId = item.getCategoryId();
            //根据分类的id来查询分类的对象
            Category category = categoryService.getById(categoryId);
            if(category!=null)
            {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);

            }
            return  setmealDto;
        }).collect(Collectors.toList());
        DtoPage.setRecords(list);
        return  R_.success(DtoPage);


    }
    @DeleteMapping
    public R_<String> delete(@RequestParam List<Long> ids)
    {
        log.info("ids:{}",ids);
        setMealService.removeWithDish(ids);
        return  R_.success("套餐数据删除成功");

    }
    @GetMapping("list")
    public R_<List<Setmeal>>list( Setmeal setmeal)
    {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        setmealLambdaQueryWrapper.eq(setmeal.getStatus()!=null,Setmeal::getStatus,setmeal.getStatus());
        List<Setmeal> list = setMealService.list(setmealLambdaQueryWrapper);
        return R_.success(list);
    }
}
