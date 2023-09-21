package com.demo.controller.interceptor;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.exception.SystemException;
import com.demo.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
@Slf4j
public class AuthHandlerInterceptor implements HandlerInterceptor {

    //使用Users的缓存空间，key过期时间为1小时
    @CreateCache(area="users",name="token_",expire=3600)
    Cache<String,String> jetCache;

    @Autowired
    TokenUtil tokenUtil;

    //token自动刷新时间
    @Value("${jwt.refreshTime}")
    private long refreshTime;

    //token过期时间
    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

        log.info("=======进入拦截器========");
        System.out.println("=======进入拦截器========");
//        // 如果不是映射到方法直接通过,可以访问资源.
//        if (object instanceof HandlerMethod){
//            return true;
//        }

        //没有token时，拦截不放行
        String token = request.getHeader("token");

        if (null == token || "".equals(token.trim())){
            throw new BusinessException(Code.SYSTEM_TOKEN_INVALID_ERR,"token参数缺失");
        }
        log.info("==============token:" + token);

        Map<String, String> stringMap = tokenUtil.parseToken(token);

        //判断从创建token到当前时间的总使用时间
        long useTime =System.currentTimeMillis() - Long.parseLong(stringMap.get("timeStamp"));
        //获取用户Id
        String userId = stringMap.get("userId");
        System.out.println("userId："+userId);
        //获取缓存中的token进行比较
        String tokenKey = jetCache.get(userId);

        if (!tokenKey.equals(token)){
            throw new SystemException(Code.SYSTEM_TOKEN_INVALID_ERR,"token失效，请重新获取");
        }

        //获取用户角色
        String userRole = stringMap.get("userRole");

        //使用时间和刷新时间、过期时间进行比较
        //使用时间超过过期时间
        if (useTime <= refreshTime){
            log.info("token验证成功");
            return true;
        }else if(useTime > refreshTime && useTime < expiration){
            //超过刷新时间，不超过过期时间，重新获取一次token
            response.setHeader("token",tokenUtil.getToken(userId,userRole));
            log.info("token刷新成功");
            return true;
        }else{
            throw new SystemException(Code.SYSTEM_TOKEN_TIMEOUT_ERR,"token失效，请重新登陆");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //请求处理完成后待处理的
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求处理完后，且不管抛异常与否都要处理的
    }
}
