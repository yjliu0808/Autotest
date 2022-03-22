package com.grey.workflow.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grey.workflow.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 成功处理器:响应json结果给前端进行处理，比如：跳转到首页
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String json = objectMapper.writeValueAsString(Result.ok("认证成功"));
        response.getWriter().write(json);
    }

}
