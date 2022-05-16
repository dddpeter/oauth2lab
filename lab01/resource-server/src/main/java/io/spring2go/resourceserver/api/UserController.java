package io.spring2go.resourceserver.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	// 资源API
    @RequestMapping("/api/userinfo")
    public ResponseEntity<UserInfo> getUserInfo() {
        String name = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        String email = name + "@spring2go.com";
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setEmail(email);
        return ResponseEntity.ok(userInfo);
    }

}