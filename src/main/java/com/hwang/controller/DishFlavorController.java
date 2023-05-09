package com.hwang.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.DishFlavor;
import com.hwang.service.DishFlavorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 菜品口味关系表(DishFlavor)表控制层
 *
 * @author makejava
 * @since 2023-05-04 22:16:29
 */
@RestController
@RequestMapping("dishFlavor")
public class DishFlavorController{
    /**
     * 服务对象
     */
    @Resource
    private DishFlavorService dishFlavorService;

}

