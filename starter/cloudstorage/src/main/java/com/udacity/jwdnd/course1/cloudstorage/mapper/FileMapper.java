package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFile(String fileName);

    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File getFileById(Integer fileId);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFileById(int fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getAllFilesByUserId(int userId);

}
