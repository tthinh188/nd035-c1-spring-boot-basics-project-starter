package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    FileService fileService;

    public HomeController(FileService fileService) { this.fileService = fileService; }

    @GetMapping("/home")
    public String getHomepage(Model model, User user, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        model.addAttribute("fileList", fileService.getFiles(fileService.getUserID(username)));
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logOut( ) {
        return "login";
    }

    @GetMapping("/result")
    public String showResult(
            Authentication authentication,
            @RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
            @RequestParam(required = false, name = "errorType") String errorType,
            Model model
    ) {

        Map<String, Object> attributes = new HashMap<>();

        attributes.put("isSuccess", isSuccess);
        attributes.put("errorType", errorType);

        model.addAllAttributes(attributes);

        return "result";
    }

}
