package com.gj.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VerificationCodeService {

    @Autowired
    private JavaMailSender mailSender;

    // 存储验证码的Map: key是邮箱，value是CodeInfo对象
    private final Map<String, CodeInfo> codeMap = new ConcurrentHashMap<>();

    // 验证码有效期（分钟）
    private static final int EXPIRE_MINUTES = 5;

    // 验证码长度
    private static final int CODE_LENGTH = 6;

    // 内部类，存储验证码信息
    private static class CodeInfo {
        String code;
        LocalDateTime expireTime;

        public CodeInfo(String code, LocalDateTime expireTime) {
            this.code = code;
            this.expireTime = expireTime;
        }
    }

    // 生成并发送验证码
    public void sendCode(String email) {
        // 生成6位随机验证码
        String code = generateVerificationCode();

        // 设置过期时间
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(EXPIRE_MINUTES);

        // 存储验证码信息
        codeMap.put(email, new CodeInfo(code, expireTime));

        // 发送邮件
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setFrom(new InternetAddress("GJdot123456@163.com"));
            mimeMessage.setSubject("验证码");
            mimeMessage.setText("亲爱的用户，您的验证码为: " + code + "，" + EXPIRE_MINUTES + "分钟内有效。");
        };

        try {
            mailSender.send(preparator);
        } catch (Exception e) {
            // 发送失败时从Map中移除
            codeMap.remove(email);
            throw new RuntimeException("验证码发送失败: " + e.getMessage());
        }
    }

    // 验证码校验
    public boolean verifyCode(String email, String code) {
        CodeInfo codeInfo = codeMap.get(email);
        if (codeInfo == null) {
            return false;
        }

        // 验证码匹配且未过期
        boolean isValid = codeInfo.code.equals(code) &&
                LocalDateTime.now().isBefore(codeInfo.expireTime);

        return isValid;
    }

    // 生成验证码
    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }

    // 定时清理过期的验证码
    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void cleanExpiredCodes() {
        LocalDateTime now = LocalDateTime.now();
        codeMap.entrySet().removeIf(entry ->
                entry.getValue().expireTime.isBefore(now)
        );
    }
}