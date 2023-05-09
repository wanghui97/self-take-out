package com.hwang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.hwang.annotation.PrintLogs;
import com.hwang.entity.User;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.service.UserService;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import com.hwang.utiltools.SMSUtils;
import com.hwang.utiltools.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @Author 王辉
 * @Create 2023/4/27 18:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class ReceptionLoginController {
    @Autowired
    private UserService userService;

    /**
     * 发送手机短信验证码
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    @PrintLogs(BusinessName = "发送手机短信验证码")
    public ResponseResult sendMsg(@RequestBody User user){
        //获取手机号
        String phone = user.getPhone();
        if(StringUtils.isNotEmpty(phone)){
            if(phone.length()!=11){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
            }
            //生成随机的4位验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code={}",code);
            //调用阿里云提供的短信服务API完成发送短信
//            SMSUtils.sendMessage("个人外卖","",phone,code);
            //需要将生成的验证码保存到Session
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpSession session = requestAttributes.getRequest().getSession();
            session.setAttribute(phone,code);
            HashMap<String, String> map = new HashMap<>();
            map.put("code",code);
            return ResponseResult.okResult(map);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.SEND_FAIL);
    }

    /**
     * 移动端用户登录
     * @param map
     * @return
     */
    @PostMapping("/login")
    @PrintLogs(BusinessName = "移动端用户登录")
    public ResponseResult login(@RequestBody Map map){
        log.info(map.toString());
        //获取手机号
        String phone = map.get("phone").toString();
        if(!StringUtils.isNotEmpty(phone)||phone.length()!=11){
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //获取验证码
        String code = map.get("code").toString();
        //从Session中获取保存的验证码
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        Object codeInSession = session.getAttribute(phone);
        //进行验证码的比对（页面提交的验证码和Session中保存的验证码比对）
        if(codeInSession != null && codeInSession.equals(code)){
            //如果能够比对成功，说明登录成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user == null){
                //判断当前手机号对应的用户是否为新用户，如果是新用户就自动完成注册
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute(CodeLibraryUtil.RECEPTION_USER,user.getId());
            return ResponseResult.okResult(user);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
    }

    /**
     * 后台用户退出登录接口
     * @return
     */
    @PostMapping("/loginout")
    @PrintLogs(BusinessName = "后台用户退出登录接口")
    public ResponseResult logout(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        request.getSession().removeAttribute(CodeLibraryUtil.EMPLOYEE_SESSION);
        return ResponseResult.okResult();
    }
}
