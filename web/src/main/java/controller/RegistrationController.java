package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testApp.User;
import org.testApp.UserDetails;
import org.testApp.api.UserService;
import org.testApp.api.Validator;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private Validator userValidator;
    private UserService userService;

    public RegistrationController(Validator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
    }

    @GetMapping()
    public String registration() {
        return "registrationPage";
    }

    @PostMapping()
    public String registration(HttpServletRequest request) {
        String userLogin = request.getParameter("login");
        if (userValidator.checkLoginInDB(userLogin)) {
            request.setAttribute("UserExistsMessage", "Логин занят");
            return "registrationPage";
        }
        String userPassword = request.getParameter("password");
        String userEmail = request.getParameter("email");
        User user = new User(userLogin, userPassword, userEmail);
        UserDetails userDetails = new UserDetails(null, null, null, null, user);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");
        if (!name.isEmpty()) {
            userDetails.setName(name);
        }
        if (!surname.isEmpty()) {
            userDetails.setSurname(surname);
        }
        if (!age.isEmpty()) {
            userDetails.setAge(Integer.parseInt(age));
        }
        user.setuDetails(userDetails);
        userService.addUser(user);
        request.setAttribute("registrationSuccessMessage", "Регистрация прошла успешно");
        return "indexPage";
    }



}
