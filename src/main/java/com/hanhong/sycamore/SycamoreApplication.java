package com.hanhong.sycamore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hanhong.sycamore.**.mapper")
public class SycamoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SycamoreApplication.class, args);
	}

}
