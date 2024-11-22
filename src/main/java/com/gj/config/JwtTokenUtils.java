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

    /**
     * 生成 JWT Token
     *
     * @param userId   用户ID
     * @param password 用户密码
     * @return 生成的 Token
     */
    public static String genToken(String userId, String password) {
        try {
            return JWT.create()
                    .withAudience(userId) // 将 userId 作为受众
                    .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 设置过期时间
                    .sign(Algorithm.HMAC256(password)); // 使用密码作为签名密钥
        } catch (Exception e) {
            log.error("生成 Token 时发生错误", e);
            return null;
        }
    }

    /**
     * 获取当前请求用户信息
     *
     * @return 用户对象或 null
     */
    public User getCurrentUser() {
        String token = null;
        try {
            HttpServletRequest request = getCurrentHttpRequest();
            if (request == null) {
                log.error("无法获取当前请求");
                return null;
            }

            // 从 Header 或参数中获取 Token
            token = request.getHeader("token");
            if (StrUtil.isBlank(token)) {
                token = request.getParameter("token");
            }

            if (StrUtil.isBlank(token)) {
                log.error("Token 为空");
                return null;
            }

            // 解析 Token
            String userId = JWT.decode(token).getAudience().get(0); // 获取受众信息
            return userService.findById(Integer.parseInt(userId));
        } catch (JWTDecodeException e) {
            log.error("Token 解析失败: {}", token, e);
            return null;
        } catch (Exception e) {
            log.error("获取当前用户信息时发生错误", e);
            return null;
        }
    }

    /**
     * 获取当前 HTTP 请求
     *
     * @return HttpServletRequest 对象或 null
     */
    private HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return (HttpServletRequest) attributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        }
        return null;
    }
}
