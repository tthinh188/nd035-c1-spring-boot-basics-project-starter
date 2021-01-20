package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    NoteService noteService;
    UserMapper userMapper;

    public NoteController(NoteService noteService, UserMapper userMapper) {
        this.noteService = noteService;
        this.userMapper = userMapper;
    }

    @PostMapping("/note")
    public String submitNote(@ModelAttribute("note") Note note, Authentication authentication) {
        String userName = authentication.getPrincipal().toString();
        noteService.insertOrUpdateNote(note, userMapper.getUser(userName));

        return "redirect:/result?isSuccess=" + true;
    }

    @GetMapping("/delete")
    public String deleteNote(@ModelAttribute("note") Note note,
                             @RequestParam(required = false, name = "noteId") int noteId,
                             Authentication authentication) {
        String userName = authentication.getPrincipal().toString();

        noteService.deleteNote(note);
        return "redirect:/result?isSuccess=" + true;

    }
}
