package com.beta.replyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServiceApplication {
//	@Autowired
//	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
//		Map<String,HashAlgoService> map = applicationContext.getBeansOfType(HashAlgoService.class);
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
