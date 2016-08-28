package com.df.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;

/**
 * Created by denis on 8/27/2016.
 */
public class CookieAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String url = httpServletRequest.getRequestURL().toString();




        if (httpServletRequest.getCookies() != null) {
            Cookie c = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> cookie.getName().equals("df")).findFirst().orElse(null);
            if (c != null) {


                try {
                    //String token = AES.decrypt(c.getValue().getBytes(), AES.encryptionKey);

                    String token = URLDecoder.decode(c.getValue(), "UTF-8");
                    CustomUser user = new ObjectMapper().readValue(token, CustomUser.class);




                    Authentication auth = new UsernamePasswordAuthenticationToken(user,user.getPassword(), user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        Cookie redirectCookie = new Cookie("target_url", url);
        httpServletResponse.addCookie(redirectCookie);

        filterChain.doFilter(httpServletRequest, httpServletResponse);


    }


}
