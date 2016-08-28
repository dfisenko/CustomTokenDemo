package com.df.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Created by denis on 8/27/2016.
 */
public class MyAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private PasswordEncoder encoder;

    public MyAuthSuccessHandler(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {


        CustomUser user = (CustomUser) authentication.getPrincipal();

        String token = new ObjectMapper().writeValueAsString(user);


        try {
            Cookie c = new Cookie("df", URLEncoder.encode(token, "UTF-8"));

            httpServletResponse.addCookie(c);

            String redirectUrl = getRedirectUrl(httpServletRequest);
            Cookie redirectCookie = new Cookie("target_url", "");
            httpServletResponse.addCookie(redirectCookie);

            httpServletResponse.sendRedirect(redirectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.sendRedirect("/error");
        }

    }

    private String getRedirectUrl(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            Cookie redirectCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("target_url")).findFirst().orElse(null);
            return redirectCookie != null ? redirectCookie.getValue() : "/";
        } else {
            return "/";
        }
    }
}
