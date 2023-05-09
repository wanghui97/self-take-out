package com.hwang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwang.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * 用户信息(User)表数据库访问层
 *
 * @author hwang
 * @since 2023-05-06 16:03:32
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
