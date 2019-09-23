package base.controller;

import base.model.Role;
import base.model.User;
import base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserViewController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/home/news", method = RequestMethod.GET)
    public ModelAndView viewUserPage() {
        ModelAndView mv = new ModelAndView("home");
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByLogin(principal.getName());
        mv.addObject("user", user.getName());
        //mv.addObject("user_id", user.getId());
        return mv;
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
