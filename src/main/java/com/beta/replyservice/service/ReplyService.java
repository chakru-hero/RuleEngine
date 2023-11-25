package com.beta.replyservice.service;


import com.beta.replyservice.exception.InvalidInputException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface ReplyService {

    public String processString(String string, String hashAlgo) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidInputException;
//    public String hashString(String string, String hashAlgo) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
