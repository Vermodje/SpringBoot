package base.controller;

import base.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class UserViewController {


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String viewUserPage() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLoginPage() {
        if (AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains("ROLE_ADMIN")) {
            return "redirect:/admin";

        } else if (AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains("ROLE_USER")) {
            return "redirect:/home";
        } else {
            return "login";
        }
    }
}
