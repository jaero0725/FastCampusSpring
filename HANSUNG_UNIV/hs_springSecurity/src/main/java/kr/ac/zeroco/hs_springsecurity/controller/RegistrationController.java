package kr.ac.zeroco.hs_springsecurity.controller;

import kr.ac.zeroco.hs_springsecurity.entity.Role;
import kr.ac.zeroco.hs_springsecurity.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import kr.ac.zeroco.hs_springsecurity.entity.User;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (registrationService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "signup";
        }
        else {
            Role role = registrationService.findByRolename("ROLE_USER");

            List<Role> userRoles = new ArrayList<>();
            userRoles.add(role);

            registrationService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
}
