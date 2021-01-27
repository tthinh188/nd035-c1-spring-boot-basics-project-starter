package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    FileService fileService;
    NoteService noteService;
    CredentialService credentialService;
    UserService userService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, UserService userService ) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage(
            @ModelAttribute("note") Note note,
            @ModelAttribute("credential") Credential credential,
            Model model, Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        model.addAttribute("fileList", fileService.getFiles(fileService.getUserID(username)));
        model.addAttribute("noteList", noteService.getNotes(fileService.getUserID(username)));
        model.addAttribute("credentialList", credentialService.getCredentialList(fileService.getUserID(username)));
        return "home";
    }

    @GetMapping("/logout")
    public String logOut( ) {
        return "logout";
    }

    @GetMapping("/login")
    public String loginPage( ) {
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
