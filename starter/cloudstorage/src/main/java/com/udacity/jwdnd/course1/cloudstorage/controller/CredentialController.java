package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("credentials")
public class CredentialController {
    CredentialService credentialService;
    UserMapper userMapper;

    public CredentialController(CredentialService credentialService, UserMapper userMapper) {
        this.credentialService = credentialService;
        this.userMapper = userMapper;
    }

    @PostMapping("/credential")
    public String submitCredential(@ModelAttribute("credential") Credential credential,
                                   Authentication authentication) {
        String userName = authentication.getPrincipal().toString();
        credentialService.insertOrUpdateCredential(credential, userMapper.getUser(userName));
        return "redirect:/result?isSuccess=" + true;
    }

    @GetMapping("/delete")
    public String deleteCredential(@ModelAttribute("credential") Credential credential,
                                   @RequestParam(required = false, name = "credentialId") int credentialId) {
        credentialService.deleteCredential(credential);
        return "redirect:/result?isSuccess=" + true;
    }

}
