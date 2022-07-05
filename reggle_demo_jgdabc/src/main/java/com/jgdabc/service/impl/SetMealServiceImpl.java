package com.jgdabc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jgdabc.common.CustomException;
import com.jgdabc.dto.SetmealDto;
import com.jgdabc.entity.Setmeal;
import com.jgdabc.entity.SetmealDish;
import com.jgdabc.mapper.SetMealMapper;
import com.jgdabc.service.SetMealService;
import com.jgdabc.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService{
//   新增套餐，同时保存套餐和菜品的关联关系
    @Autowired
    private SetmealDishService setmealDishService;

    @Override
    @Transactional //加入事务保证数据的一致性
    public void saveWithDish(SetmealDto setmealDto) {
//        保存套餐的基本信息
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->
        {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
//        保存套餐和菜品的关联信息
        setmealDishService.saveBatch(setmealDishes);


    }
//    删除套餐同时删除套餐和菜品的关联数据
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
//        查询套餐的状态确定是否可以删除
//        要根据套餐的状态，是在售卖还是停止售卖
//        如果了可以删除，删除套餐中的数据，然后删除关联表当中的数据
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId,ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus,1);
        long count = this.count(setmealLambdaQueryWrapper);
        if(count>0)
        {
            throw  new CustomException("套餐正在售卖，不能删除");
        }
        this.removeBatchByIds(ids);
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
