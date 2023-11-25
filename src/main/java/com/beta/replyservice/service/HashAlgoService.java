package com.beta.replyservice.service;

import java.security.NoSuchAlgorithmException;

public interface HashAlgoService {
    String hash(String string) throws NoSuchAlgorithmException;
}
