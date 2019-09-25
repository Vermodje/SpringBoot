package base.controller;

import base.exception.UserException;
import base.model.News;
import base.model.Role;
import base.model.User;
import base.service.NewsService;
import base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/home/news", method = RequestMethod.GET)
    public String viewNewsPage() {
        return "news";
    }
    @RequestMapping(value = "/home/news/{id}",method = RequestMethod.GET)
    public ModelAndView viewCheckedNewsPage(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("home");
        try {
            mv.addObject("headline", newsService.getNewsById(id).getHeadline());
            mv.addObject("description", newsService.getNewsById(id).getDescription());
            mv.addObject("newsId", newsService.getNewsById(id).getId());

        } catch (UserException e) {
            e.printStackTrace();
        }
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
