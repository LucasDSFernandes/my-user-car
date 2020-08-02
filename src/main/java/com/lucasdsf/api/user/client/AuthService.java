package com.lucasdsf.api.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucasdsf.api.user.client.config.AuthServiceConfiguration;
import com.lucasdsf.api.user.domains.model.UserDto;

@FeignClient(name = "${auth.api.name}", url = "${auth.api.url}", configuration = AuthServiceConfiguration.class)
public interface AuthService {

    @RequestMapping(path = "/api/refresh/token", method = RequestMethod.GET)
    UserDto getPrincipal(@RequestHeader("Authorization") String authorization);
}