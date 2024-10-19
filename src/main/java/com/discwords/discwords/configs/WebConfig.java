package com.discwords.discwords.configs;


import com.discwords.discwords.filters.AuthorizationFilter;
import com.discwords.discwords.repository.ProfileRepo;
import com.discwords.discwords.repository.UserRepo;
import com.discwords.discwords.service.JWTService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    private final JWTService jwtService;
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;

    public WebConfig(JWTService jwtService, UserRepo userRepo, ProfileRepo profileRepo){
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> customFilter(){
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthorizationFilter(jwtService, userRepo, profileRepo));
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
