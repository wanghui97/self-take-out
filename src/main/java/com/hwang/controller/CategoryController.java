package com.hwang.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.Category;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.CategoryService;
import com.hwang.utiltools.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜品及套餐分类(Category)表控制层
 *
 * @author hwang
 * @since 2023-05-04 10:44:04
 */
@RestController
@RequestMapping("category")
public class CategoryController{
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 当前页码
     * @param pageSize 页面大小
     * @return 所有数据
     */
    @GetMapping("/page")
    @PrintLogs(BusinessName = "分页查询分类数据")
    public ResponseResult selectAll(Long page, Long pageSize) {
        //由于前端参数中的字段名和page对象中的属性名不一致，无法映射，所以手动包装
        Page categoryPage = new Page();
        categoryPage.setCurrent(page);
        categoryPage.setSize(pageSize);
        return categoryService.queryCategoryPage(categoryPage);
    }
    /**
     * 根据分类类型获取分类信息
     * @param type 类型
     * @return 所有数据
     */
    @GetMapping("/list")
    @PrintLogs(BusinessName = "根据分类类型获取分类信息")
    public ResponseResult selectCategoryByType(Integer type) {
        return categoryService.selectCategoryByType(type);
    }


    /**
     * 新增数据
     *  新增分类信息
     * @param category 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PrintLogs(BusinessName = "新增分类信息")
    public ResponseResult insert(@RequestBody Category category) {
        boolean saveFlag = categoryService.save(category);
        if(!saveFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    /**
     * 修改分类数据
     * @param category 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PrintLogs(BusinessName = "修改分类数据")
    public ResponseResult update(@RequestBody Category category) {
        boolean updateFlag = categoryService.updateById(category);
        if(!updateFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    /**
     * 删除分类数据
     * @param ids 主键集合
     * @return 删除结果
     */
    @DeleteMapping
    @PrintLogs(BusinessName = "删除分类数据")
    public ResponseResult delete(@RequestParam("ids") List<Long> ids) {
        boolean removeFlag = categoryService.removeByIds(ids);
        if(!removeFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }
}

