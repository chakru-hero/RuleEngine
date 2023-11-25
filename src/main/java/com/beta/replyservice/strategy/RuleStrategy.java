package com.beta.replyservice.strategy;

import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.HashAlgoService;
import com.beta.replyservice.service.impl.MD5HashImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Component
public class RuleStrategy {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private HashAlgoService hashAlgoService;

    public String getStringAsPerRule(String rule,String inputString) throws InvalidInputException, NoSuchAlgorithmException {
        if (rule.equals("1")) {
            inputString = new StringBuffer(inputString).reverse().toString();
            } else if (rule.equals("2")) {
            hashAlgoService = getImplementation(MD5HashImpl.class.getSimpleName());
            inputString = hashAlgoService.hash(inputString);
            } else {
                throw new InvalidInputException("Invalid Input");
            }
        return inputString;
    }
    HashAlgoService getImplementation(String className){
        Map<String,HashAlgoService> HashAlgoImplMap = applicationContext.getBeansOfType(HashAlgoService.class);
        return HashAlgoImplMap.get(className);
    }
}
