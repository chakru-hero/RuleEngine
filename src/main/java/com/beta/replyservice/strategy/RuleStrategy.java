package com.beta.replyservice.strategy;

import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.impl.MD5HashImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
public class RuleStrategy {
    @Autowired
    private MD5HashImpl md5Hash;

    public String getStringAsPerRule(String rule,String inputString) throws InvalidInputException, NoSuchAlgorithmException {
        if (rule.equals("1")) {
            inputString = new StringBuffer(inputString).reverse().toString();
            } else if (rule.equals("2")) {
            inputString = md5Hash.hash(inputString);
            } else {
                throw new InvalidInputException("Invalid Input");
            }
        return inputString;
    }
}
