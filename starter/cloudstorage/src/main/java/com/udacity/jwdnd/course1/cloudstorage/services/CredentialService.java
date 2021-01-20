package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;

    public CredentialService (CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentialList(int userId) {
        return credentialMapper.getCredentials(userId);
    }

    public void insertOrUpdateCredential(Credential credential, User user) {
        if (credential.getCredentialId() == null || credential.getCredentialId().toString().equals("")) {
            Credential addedCredential = new Credential(0, credential.getURL(), credential.getUserName(), credential.getKey(), credential.getPassword(), user.getUserId());
            credentialMapper.insert(addedCredential);
        }
        else {
            credentialMapper.update(credential.getCredentialId(), credential.getURL(), credential.getUserName(), credential.getKey(), credential.getPassword());
        }
    }
}
