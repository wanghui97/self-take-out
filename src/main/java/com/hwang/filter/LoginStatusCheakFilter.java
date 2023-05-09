package com.hwang.filter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.hwang.enums.AppHttpCodeEnum;
import com.hwang.utiltools.BaseContextUtil;
import com.hwang.utiltools.CodeLibraryUtil;
import com.hwang.utiltools.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Description:
 *  检查用户是否已经完成登录
 * @Author 王辉
 * @Create 2023/5/4 9:03
 * @Version 1.0
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginStatusCheakFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();// /backend/index.html
        log.info("拦截到请求：{}",requestURI);
        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        //2、判断本次请求是否需要处理,如果是true则不需要处理，为false需要处理
        boolean checkResult = checkUri(urls, requestURI);
        //3、如果不需要处理，则直接放行
        if(checkResult){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4-1、判断后台用户登录状态，如果已登录，则直接放行
        if(Objects.nonNull(request.getSession().getAttribute(CodeLibraryUtil.EMPLOYEE_SESSION))){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute(CodeLibraryUtil.EMPLOYEE_SESSION));
            Long empId = (Long) request.getSession().getAttribute(CodeLibraryUtil.EMPLOYEE_SESSION);
            BaseContextUtil.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }

        //4-2、判断前台用户登录状态，如果已登录，则直接放行
        if(Objects.nonNull(request.getSession().getAttribute(CodeLibraryUtil.RECEPTION_USER))){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute(CodeLibraryUtil.RECEPTION_USER));
            Long userId = (Long) request.getSession().getAttribute(CodeLibraryUtil.RECEPTION_USER);
            BaseContextUtil.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }

        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(ResponseResult.errorResult(AppHttpCodeEnum.NOT_LOGIN)));
        return;

    }
    public boolean checkUri(String[] urls,String requestURI){
        boolean uriIsExist = Arrays.stream(urls)
                .anyMatch(url -> PATH_MATCHER.match(url, requestURI));
        return uriIsExist;
    }
}
