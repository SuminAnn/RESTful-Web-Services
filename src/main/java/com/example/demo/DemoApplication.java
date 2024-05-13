package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		// ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);

		// String[] allBean = ac.getBeanDefinitionNames();
		// for (String string : allBean) {
		// 	System.out.println(string);
		// }
	}

}
