package com.hwang.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.OrderDetail;
import com.hwang.service.OrderDetailService;
import com.hwang.utiltools.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 订单明细表(OrderDetail)表控制层
 *
 * @author hwang
 * @since 2023-05-06 09:15:14
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController{
    /**
     * 服务对象
     */
    @Resource
    private OrderDetailService orderDetailService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @PrintLogs(BusinessName = "通过主键查询订单明细")
    public ResponseResult selectOne(@PathVariable Serializable id) {
        return null;
    }

    /**
     * 新增数据
     *
     * @param orderDetail 实体对象
     * @return 新增结果
     */
    @PostMapping
    @PrintLogs(BusinessName = "新增订单明细")
    public ResponseResult insert(@RequestBody OrderDetail orderDetail) {
        return null;
    }

    /**
     * 修改数据
     *
     * @param orderDetail 实体对象
     * @return 修改结果
     */
    @PutMapping
    @PrintLogs(BusinessName = "修改员工信息")
    public ResponseResult update(@RequestBody OrderDetail orderDetail) {
        return null;
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    @PrintLogs(BusinessName = "修改员工信息")
    public ResponseResult delete(@RequestParam("idList") List<Long> idList) {
        return null;
    }
}

