package com.beta.replyservice.service.impl;

import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Value("${Hash.Algo.Value}")
    private String DEFAULTALGO;
    @Override
    public String processString(String string, String hashAlgo) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidInputException {
        String[] strings = string.split("-");
        String[] rules= strings[0].split("");
        for (String rule : rules) {
            if (rule.equals("1")) {
                strings[1] = new StringBuffer(strings[1]).reverse().toString(); // is new StringBuffer needed? check if it can be reused.
            } else if (rule.equals("2")) {
                strings[1] = hashString(strings[1], Objects.isNull(hashAlgo) ? DEFAULTALGO : hashAlgo);
            } else {
                throw new InvalidInputException("Invalid Input");
            }
        }
        return strings[1];
    }

    @Override
    public String hashString(String string, String hashAlgo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashAlgo);
        byte[] messageDigest = md.digest(string.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
