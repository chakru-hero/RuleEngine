package com.beta.replyservice.service.impl;

import com.beta.replyservice.constants.HashAlgoConstants;
import com.beta.replyservice.service.HashAlgoService;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MD5HashImpl implements HashAlgoService {
    @Override
    public String hash(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HashAlgoConstants.MD5);
        byte[] messageDigest = md.digest(string.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
