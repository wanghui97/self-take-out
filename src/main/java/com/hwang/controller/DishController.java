package com.hwang.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.Dish;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.DishService;
import com.hwang.utiltools.BeanCopyUtils;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.DishVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理(Dish)表控制层
 *
 * @author hwang
 * @since 2023-05-04 15:35:23
 */
@RestController
@RequestMapping("dish")
public class DishController{
    /**
     * 服务对象
     */
    @Resource
    private DishService dishService;

    /**
     * 分页查询所有数据
     *
     * @param page 当前页码
     * @param pageSize 页面大小
     * @return 所有数据
     */
    @GetMapping("/page")
    @PrintLogs(BusinessName = "分页查询菜品管理数据")
    public ResponseResult selectAll(Long page, Long pageSize,String name) {
        //由于前端参数中的字段名和page对象中的属性名不一致，无法映射，所以手动包装
        Page dishPage = new Page();
        dishPage.setCurrent(page);
        dishPage.setSize(pageSize);
        return dishService.queryCategoryPage(dishPage,name);
    }

    /**
     * 根据id查询菜品信息
     * @param id
     * @return 所有数据
     */
    @GetMapping("/{id}")
    @PrintLogs(BusinessName = "根据id查询菜品信息")
    public ResponseResult selectByDishId(@PathVariable("id") Long id) {
       return dishService.selectByDishId(id);
    }
    /**
     * 根据分类id查询口味信息
     * @param categoryId 分类id
     * @return 所有数据
     */
    @GetMapping("/list")
    @PrintLogs(BusinessName = "根据分类id查询口味信息列表")
    public ResponseResult selectByCategoryId(Long categoryId,Integer status) {
        return dishService.selectByCategoryId(categoryId,status);
    }
    /**
     * 新增数据
     *
     * @param dishVo 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PrintLogs(BusinessName = "新增菜品数据")
    public ResponseResult insert(@RequestBody DishVo dishVo) {
        return dishService.saveDish(dishVo);
    }

    /**
     * 修改数据
     *
     * @param dishVo 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PrintLogs(BusinessName = "修改菜品数据")
    public ResponseResult update(@RequestBody DishVo dishVo) {
        return dishService.updateDish(dishVo);
    }

    /**
     * 修改数据状态
     * @param updateStatus 需要更新的状态
     * @param ids 需要更新的对象id集合
     * @return 修改结果
     */
    @PostMapping("/status/{updateStatus}")
    @PrintLogs(BusinessName = "修改菜品数据状态")
    public ResponseResult updateStatus(@PathVariable int updateStatus,Long[]ids) {
        List<Dish> dishList = Arrays.stream(ids)
                .map(id -> new Dish(id, updateStatus))
                .collect(Collectors.toList());
        boolean batchByIdFlag = dishService.updateBatchById(dishList);
        if(!batchByIdFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    /**
     * 删除数据
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PrintLogs(BusinessName = "删除菜品数据")
    public ResponseResult delete(@RequestParam("ids") List<Long> idList) {
        boolean removeFlag = dishService.removeByIds(idList);
        if(!removeFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }
}

