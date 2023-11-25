package com.beta.replyservice.service.impl;

import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.HashAlgoService;
import com.beta.replyservice.service.ReplyService;
import com.beta.replyservice.strategy.RuleStrategy;
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
    @Autowired
    private RuleStrategy ruleStrategy;
    @Override
    public String processString(String string, String hashAlgo) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidInputException {
        String[] rules;
        String[] strings;
            if(!string.contains("-")){
                throw new InvalidInputException("Invalid Input");
            }
            strings = string.split("-");
            rules = strings[0].split("");
        for (String rule : rules) {
            strings[1] = ruleStrategy.getStringAsPerRule(rule,strings[1]);
        }
        return strings[1];
    }
}
