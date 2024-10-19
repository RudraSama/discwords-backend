package com.discwords.discwords.filters;

import com.discwords.discwords.model.Profile;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.service.JWTService;
import com.discwords.discwords.model.User;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LogManager.getLogger();

    private final JWTService jwtService;
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    public AuthorizationFilter(JWTService jwtService, UserRepo userRepo, ProfileRepo profileRepo){
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        if(!request.getMethod().equals("OPTIONS") && request.getRequestURI().startsWith("/api")){

            if(request.getRequestURI().equals("/api/checkAuthorization")){
                filterChain.doFilter(request, response);
                return;
            }

            String token = request.getHeader("x-access-token");
            String userId = request.getHeader("userId");
            String profileId = request.getHeader("profileId");

            if(token == null){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            else if(!(jwtService.validJwtToken(token) && jwtService.isTokenValid(token, Long.parseLong(userId), Long.parseLong(profileId)))){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }


        filterChain.doFilter(request, response);
    }

}
