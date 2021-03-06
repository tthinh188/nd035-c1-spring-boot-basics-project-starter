package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getNotesByUserId(int userId);

    @Insert("INSERT INTO NOTES(noteTitle, noteDescription, userId) VALUES (" +
            "#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void delete(Integer noteId);

    @Update("UPDATE notes " +
            "SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE noteId = #{noteId}")
    int update(Integer noteId, String noteTitle, String noteDescription);

}
