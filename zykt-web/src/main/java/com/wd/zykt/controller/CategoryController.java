package com.wd.zykt.controller;

import com.wd.zykt.pojo.Category;
import com.wd.zykt.service.CategoryService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ControllerJSONResult result;


    @GetMapping("/getCategory/{id}")
    public ControllerJSONResult getCategory(@PathVariable("id") Integer id){
        // 调用service查询所有类别
        ServiceResult<LinkedList<Category>> serviceResult = categoryService.QueCategoryBySeriesId(id);
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }
    @GetMapping("/getCategoryAll")
    public ControllerJSONResult getCategoryAll(){
        // 查询全部分类
        ServiceResult<LinkedList<Category>> serviceResult = categoryService.QueCategory();
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }

}
