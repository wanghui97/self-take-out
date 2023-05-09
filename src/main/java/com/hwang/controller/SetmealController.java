package com.hwang.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.Setmeal;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.SetmealDishService;
import com.hwang.service.SetmealService;
import com.hwang.utiltools.ResponseResult;
import com.hwang.vo.SetmealVo;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐(Setmeal)表控制层
 *
 * @author hwang
 * @since 2023-05-05 10:51:59
 */
@RestController
@RequestMapping("setmeal")
public class SetmealController{
    /**
     * 服务对象
     */
    @Resource
    private SetmealService setmealService;
    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 分页查询所有数据
     *
     * @param page 当前页码
     * @param pageSize 页面大小
     * @return 所有数据
     */
    @GetMapping("/page")
    @PrintLogs(BusinessName = "分页查询套餐信息")
    public ResponseResult selectAllPage(Long page, Long pageSize, String name) {
        //由于前端参数中的字段名和page对象中的属性名不一致，无法映射，所以手动包装
        Page setmealPage = new Page();
        setmealPage.setCurrent(page);
        setmealPage.setSize(pageSize);
        return setmealService.querySetmealPage(setmealPage,name);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @PrintLogs(BusinessName = "通过主键查询套餐信息")
    public ResponseResult selectOne(@PathVariable Serializable id) {
        return setmealService.selectBySetmealId(id);
    }

    /**
     * 新增数据
     *
     * @param setmealVo 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PrintLogs(BusinessName = "新增套餐")
    public ResponseResult insertSetmeal(@RequestBody SetmealVo setmealVo) {
        return setmealService.insertSetmeal(setmealVo);
    }

    /**
     * 修改数据
     *
     * @param setmealVo 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PrintLogs(BusinessName = "修改套餐信息")
    public ResponseResult updateSetmeal(@RequestBody SetmealVo setmealVo) {
        return setmealService.updateSetmeal(setmealVo);
    }
    /**
     * 修改数据状态
     * @param updateStatus 需要更新的状态
     * @param ids 需要更新的对象id集合
     * @return 修改结果
     */
    @PostMapping("/status/{updateStatus}")
    @PrintLogs(BusinessName = "修改套餐销售状态")
    public ResponseResult updateStatus(@PathVariable int updateStatus,Long[]ids) {
        List<Setmeal> setmealList = Arrays.stream(ids)
                .map(id -> new Setmeal(id, updateStatus))
                .collect(Collectors.toList());
        boolean batchByIdFlag = setmealService.updateBatchById(setmealList);
        if(!batchByIdFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PrintLogs(BusinessName = "删除套餐信息")
    public ResponseResult delete(@RequestParam("ids") List<Long> idList) {
        boolean removeFlag = setmealService.removeByIds(idList);
        if(!removeFlag){
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }
    /**
     * 查询所有套餐数据
     * @return 所有数据
     */
    @GetMapping("/list")
    @PrintLogs(BusinessName = "根据分类id和菜品状态查询套餐数据")
    public ResponseResult selectSetmealByCategoryId(Long categoryId,Integer status) {
        return setmealService.selectSetmealByCategoryId(categoryId,status);
    }
    /**
     * 根据套餐id获取关联菜品信息
     * @return 所有数据
     */
    @GetMapping("/dish/{id}")
    @PrintLogs(BusinessName = "查询所有套餐数据")
    public ResponseResult selectSetmealDish(@PathVariable("id") Long dishId) {
        return setmealDishService.selectSetmealDishByDishId(dishId);
    }
}

