package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialMapper {
    @Insert("INSERT INTO CREDENTIALS(URL, userName, key, password, userId) VALUES (" +
            "#{URL}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int insert(Credential credential);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredentialById(Integer credentialId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    void delete(Integer credentialId);

    @Update("UPDATE credentials " +
            "SET URL = #{URL}, userName = #{userName}, key = #{key}, " +
            "password = #{password} " +
            "WHERE credentialId = #{credentialId}")
    int update(
            String URL, String userName, String key, String password, Integer credentialId);

}
