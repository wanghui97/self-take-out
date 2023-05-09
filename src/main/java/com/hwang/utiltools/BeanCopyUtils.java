package com.hwang.utiltools;

import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description:
 *  实体类转换为vo对象工具类
 * @Author 王辉
 * @Create 2023/4/28 10:04
 * @Version 1.0
 */
public class BeanCopyUtils {
    /**
     * 拷贝单个对象
     *  source：源对象
     *  clazz：目标对象字节码对象(即目标对象类型)
     */
    public static <V> V copyBean(Object source, Class<V> clazz){
        V target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source,target);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return target;
    }
    /**
     * 拷贝集合对象
     *  sourceList:源对象集合
     *  targetList:目标对象集合
     */
    public static <E, V> List<V> copyBeanList(List<E> sourceList, Class<V> clazz){
        List<V> targetList = sourceList.stream().map(source -> copyBean(source,clazz)).collect(Collectors.toList());
        return targetList;
    }
}
