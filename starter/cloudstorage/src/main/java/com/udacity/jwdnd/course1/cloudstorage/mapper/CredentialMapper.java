package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS(URL, userName, key, password, userId) VALUES (" +
            "#{URL}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getCredentials(int userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);

    @Update("UPDATE credentials " +
            "SET URL = #{URL}, userName = #{userName}, key = #{key}, " +
            "password = #{password} " +
            "WHERE credentialId = #{credentialId}")
    int update(
            Integer credentialId, String URL, String userName, String key, String password);

}
