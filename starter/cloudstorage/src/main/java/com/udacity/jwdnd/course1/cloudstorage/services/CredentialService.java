package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;
    EncryptionService encryptionService;

    public CredentialService (CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentialList(int userId) {
        List<Credential> credentialList = credentialMapper.getCredentials(userId);
        for (Credential credential: credentialList) {
            credential.setDecryptedPassword(encryptionService.decryptValue(
                    credential.getPassword(), credential.getKey()
            ));
        }
        return credentialList;
    }

    public void insertOrUpdateCredential(Credential credential, User user) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        if (credential.getCredentialId() == null || credential.getCredentialId().toString().equals("")) {
            Credential addedCredential = new Credential(0, credential.getURL(), credential.getUserName(), encodedKey, encryptedPassword, user.getUserId());
            credentialMapper.insert(addedCredential);
        }
        else {
            credentialMapper.update(credential.getCredentialId(), credential.getURL(), credential.getUserName(), encodedKey, encryptedPassword);
        }
    }

    public void deleteCredential(Credential credential) {
        credentialMapper.delete(credential.getCredentialId());
    }
}
