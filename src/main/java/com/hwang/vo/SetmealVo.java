package com.hwang.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hwang.entity.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 套餐(Setmeal)表实体类
 *
 * @author hwang
 * @since 2023-05-05 10:51:59
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("setmeal")
@Accessors(chain = true)
public class SetmealVo {
    //主键@TableId
    private Long id;

    //菜品分类id
    private Long categoryId;
    //菜品分类名称
    private String categoryName;
    //套餐名称
    private String name;
    //套餐价格
    private Double price;
    //状态 0:停用 1:启用
    private Integer status;
    //编码
    private String code;
    //描述信息
    private String description;
    //图片
    private String image;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //创建人
    private Long createUser;
    //修改人
    private Long updateUser;
    //是否删除
    private Integer delFlag;
    //套餐菜品关系
    private List<SetmealDish> setmealDishes;
}
