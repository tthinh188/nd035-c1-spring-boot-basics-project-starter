package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    NoteMapper noteMapper;


    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(int userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public void insertOrUpdateNote(Note note, User user) {
        if (note.getNoteId() == null || note.getNoteId().toString().equals("")) {
            Note addedNote = new Note(0 , note.getNoteTitle(), note.getNoteDescription(), user.getUserId());
            noteMapper.insert(addedNote);
        }
        else {
            noteMapper.update(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());
        }
    }

    public void deleteNote(Note note) {
        noteMapper.delete(note.getNoteId());
    }
}
