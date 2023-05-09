package com.hwang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwang.entity.User;
import com.hwang.mapper.UserMapper;
import com.hwang.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息(User)表服务实现类
 *
 * @author hwang
 * @since 2023-05-06 16:03:32
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
