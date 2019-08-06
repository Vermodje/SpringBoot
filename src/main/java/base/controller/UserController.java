package base.controller;


import base.model.Role;
import base.model.User;
import base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/admin")
    public ModelAndView admin(Model model, @RequestParam(value = "message", required = false) String message,
                              @RequestParam(value = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("users", service.getAllUsers());
        model.addAttribute("user", new User());
        if (message != null) {
            mv.addObject("message", "User with same login already exist, Please try again");
        }
        if (error != null) {
            mv.addObject("error", "User with that id is not found");
        }
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Your login or password is incorrect. Please try again");
        }
        if (AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains("ROLE_ADMIN")) {
            return "redirect:/admin";

        } else if (AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities()).contains("ROLE_USER")) {
            return "redirect:/home";
        } else {
            return "login";
        }
    }

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        return model;
    }

    @RequestMapping("/admin/insert")
    public String insertUser(@ModelAttribute("user") User user, Model model) {
        try {
            service.add(user);
        } catch (Exception e) {
            model.addAttribute("message", new String());
        }
        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit")
    public ModelAndView editUser(@RequestParam long id, @RequestParam(value = "message", required = false) String message, Model model) {
        ModelAndView mv = new ModelAndView("edit");
        if (message != null) {
            mv.addObject("message", "User with same login already exist, Please try again");
        }
        try {
            mv.addObject("user", service.getUserById(id));
        } catch (Exception e) {
            model.addAttribute("message", new String());
        }

        return mv;
    }

    @RequestMapping("/admin/delete")
    public String deleteUser(@RequestParam long id, Model model) {
        try {
            service.deleteUser(id);
        } catch (Exception e) {
            model.addAttribute("error", new String());
        }
        return "redirect:/admin";
    }

    @RequestMapping("/admin/update")
    public String updateUser(@ModelAttribute("user") User user, Model model, @RequestParam long id) {
        try {
            service.updateUser(user);
        } catch (Exception e) {
            model.addAttribute("id", id);
            model.addAttribute("message", new String());
            return "redirect:/admin/edit";
        }
        return "redirect:/admin";
    }
}
