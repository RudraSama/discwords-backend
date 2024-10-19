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

        LOGGER.info("URL {}", request.getRequestURI());

        if(!request.getMethod().equals("OPTIONS") && request.getRequestURI().startsWith("/api")){
            String token = request.getHeader("x-access-token");
            if(token == null){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            else if(jwtService.validJwtToken(token)){
                long userId = Long.parseLong((String)jwtService.extractUserId(token));
                Optional<User> userRes = userRepo.findById(userId);
                if(userRes.isEmpty()){
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }

                Optional<Profile> profileRes = profileRepo.findByUserId(userId);

                if(profileRes.isEmpty()){
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
                request.setAttribute("profileId", profileRes.get().getProfileId());
            }
        }


        filterChain.doFilter(request, response);
    }

}
