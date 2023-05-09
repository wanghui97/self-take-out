package com.hwang.utiltools;

/**
 * ClassName: CodeLibraryUtil
 * Package: com.wanghui.blog.util
 * Description:
 *     定义码值常用常量
 * @Author 王辉
 * @Create 2023/3/23 15:49
 * @Version 1.0
 */
public class CodeLibraryUtil {
    /**
     * 表示数据库状态为正常
     */
    public static final String STATUS_NORMAL = "1";
    /**
     * 表示后台登录人员session
     */
    public static final String EMPLOYEE_SESSION = "employee";
    /**
     * 表示后台登录人员session
     */
    public static final String RECEPTION_USER = "user";
    /**
     * 表示菜品分类ID在CATEGORY表查不到
     */
    public static final String CATEGORY_NAME_UNDEFINED = "暂未分类";
    /**
     * 表示地址薄中某条地址信息是否默认地址：1:是，0:否
     */
    public static final int IS_DEFAULT = 1;
    /**
     * 表示地址薄中某条地址信息是否默认地址：1:是，0:否
     */
    public static final int NO_DEFAULT = 0;
    /**
     * 表示下单成功
     */
    public static final Object ORDER_SUCCESS = "下单成功";
    /**
     * 待支付
     */
    public static final Integer WAIT_PAY = 1;
}
