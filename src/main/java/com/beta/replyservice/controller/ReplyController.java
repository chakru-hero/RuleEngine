package com.beta.replyservice.controller;

import com.beta.replyservice.entity.ReplyMessage;
import com.beta.replyservice.exception.InvalidInputException;
import com.beta.replyservice.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Data is empty", null);
	}
	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		return new ReplyMessage(null, message);
	}

	//Version 2.0 starts here
	@GetMapping("v2/reply")
	public ReplyMessage replyingV2() {
		return new ReplyMessage("Data is empty", null);
	}
	@GetMapping("v2/reply/{message}")
	public ResponseEntity<ReplyMessage> newReplying(@PathVariable String message) throws InvalidInputException,UnsupportedEncodingException,NoSuchAlgorithmException{
		try {
			String data  = replyService.processString(message,null);
			return new ResponseEntity<>(new ReplyMessage(null, data), HttpStatus.OK);
		} catch (InvalidInputException exception) {
			return new ResponseEntity<>(new ReplyMessage(exception.getMessage(), null), HttpStatus.BAD_REQUEST);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}