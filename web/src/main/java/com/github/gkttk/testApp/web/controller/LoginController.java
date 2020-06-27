package com.github.gkttk.testApp.web.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.gkttk.testApp.User;
import com.github.gkttk.testApp.api.UserService;
import com.github.gkttk.testApp.api.Validator;
import com.github.gkttk.testApp.enums.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserService userService;
    private Validator userValidator;

    public LoginController(UserService userService, Validator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String login() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || "anonymousUser".equals(authentication.getPrincipal())) {
            return "indexPage";
        }
        return "redirect:/user";

    }

    @PostMapping()
    public String login(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (userValidator.checkLoginInDB(login)) {
            if (userValidator.checkUserPassword(login, password)) {
                User user = userService.getUserByLogin(login);
                Authentication auth = new UsernamePasswordAuthenticationToken(user, null, getAuthorities(user.getRole()));
                SecurityContextHolder.getContext().setAuthentication(auth);
                return "redirect:/addThemeNamesInSession";
            } else {
                request.setAttribute("UserWrongPasswordMessage", "Неправильный пароль");
                return "indexPage";
            }
        } else {
            request.setAttribute("UserNotExistsMessage", "Такой пользователь не зарегистрирован");
            return "indexPage";
        }
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        return Arrays.asList((GrantedAuthority) () -> "ROLE_" + role.toString());
    }

}
