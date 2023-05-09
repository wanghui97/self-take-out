package com.hwang.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hwang.entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 菜品管理(Dish)表实体类
 *
 * @author hwang
 * @since 2023-05-04 15:35:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("dish")
@Accessors(chain=true)
public class DishVo {
    //主键@TableId
    private Long id;

    //菜品名称
    private String name;
    //菜品分类id
    private Long categoryId;
    //菜品分类名称
    private String categoryName;
    //菜品价格
    private Double price;
    //商品码
    private String code;
    //图片
    private String image;
    //描述信息
    private String description;
    //0 停售 1 起售
    private Integer status;
    //顺序
    private Integer sort;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //创建人
    private Long createUser;
    //修改人
    private Long updateUser;
    //菜品口味关系
    private List<DishFlavor> flavors;

}
