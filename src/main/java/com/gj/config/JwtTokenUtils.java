package com.gj.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.gj.pojo.User;
import com.gj.service.iservice.IUserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Date;

@Component
public class JwtTokenUtils {
    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Resource
    private IUserService userService;


    public static String genToken(String userId, String password) {
        try {
            return JWT.create()
                    .withAudience(userId)
                    .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                    .sign(Algorithm.HMAC256(password));
        } catch (Exception e) {
            log.error("生成 Token 时发生错误", e);
            return null;
        }
    }


    public User getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = getCurrentHttpRequest();
            if (request == null) {
                log.error("无法获取当前请求");
                return null;
            }
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }

            if (StrUtil.isBlank(token)) {
                log.error("Token 为空");
                return null;
            }

            String userId = JWT.decode(token).getAudience().get(0);
            return userService.findById(Integer.parseInt(userId));
        } catch (JWTDecodeException e) {
            log.error("Token 解析失败: {}", token, e);
            return null;
        } catch (Exception e) {
            log.error("获取当前用户信息时发生错误", e);
            return null;
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return (HttpServletRequest) attributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        }
        return null;
    }
}
