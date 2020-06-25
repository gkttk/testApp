package controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.User;
import org.testApp.api.UserService;
import org.testApp.enums.Role;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/changeOwnData")
public class ChangeOwnDataController {

    private UserService userService;

    public ChangeOwnDataController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String changeOwnData(){
        return "changeOwnDataPage";
    }

    @PostMapping()
    public String changeOwnData(HttpServletRequest request) {
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        String newName = request.getParameter("newName");
        String newSurname = request.getParameter("newSurname");
        String newAge = request.getParameter("newAge");
        User oldAuthUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!newPassword.equals("")) {
            userService.changePassword(newPassword, oldAuthUser);
        }
        if (!newEmail.equals("")) {
            userService.changeEmail(newEmail, oldAuthUser);
        }
        String userLogin = oldAuthUser.getLogin();
        User currentAuthUserInDb = userService.getUserByLogin(userLogin);
        if (!newName.isEmpty()) {
            currentAuthUserInDb.getuDetails().setName(newName);
        }
        if (!newSurname.isEmpty()) {
            currentAuthUserInDb.getuDetails().setSurname(newSurname);
        }
        if (!newAge.isEmpty()) {
            currentAuthUserInDb.getuDetails().setAge(Integer.parseInt(newAge));
        }
        userService.updateUser(currentAuthUserInDb);
        User authUser = userService.getUserByLogin(userLogin);
        Authentication auth = new UsernamePasswordAuthenticationToken(authUser, null, getAuthorities(authUser.getRole()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/user";
    }

    private List<GrantedAuthority> getAuthorities(Role role) {
        return Arrays.asList((GrantedAuthority) () -> "ROLE_" + role.toString());
    }

}
