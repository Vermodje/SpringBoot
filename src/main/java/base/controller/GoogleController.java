package base.controller;

import base.service.OAuth20GoogleService;
import base.service.RoleService;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

@Controller
public class GoogleController {

    @Autowired
    private OAuth20GoogleService googleService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login-google", method = RequestMethod.GET)
    public String login() {
        OAuth20Service oAuth20Service = googleService.getService();
        return "redirect:" + googleService.getAuth(oAuth20Service);
    }
    @GetMapping(value = "/callback")
    public String google(@RequestParam String code, HttpServletResponse servletResponse){
        try {
            OAuth20Service oAuth20Service = googleService.getService();
            OAuth2AccessToken token = googleService.getToken(code, oAuth20Service);
            OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v1/userinfo?alt=json");
            oAuth20Service.signRequest(token, request);
            Response response = googleService.getResponse(oAuth20Service, request);
            String json = response.getBody();
            System.out.println(json);
            Map<String, String> mapFromJSON = googleService.convertJSONtoMap(json);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(mapFromJSON.get("name"), "", Collections.singleton(roleService.getRoleById((long) 2))));
        }catch (Exception e){
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return "redirect:/login";
    }
}
