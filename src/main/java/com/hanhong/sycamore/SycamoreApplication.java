package com.hanhong.sycamore;
/**
 * Sycamore Restaurant System
 *
 * Copyright (c) 2026 sycamore team. All rights reserved.
 *
 * 免责声明：
 * 本代码仅供内部开发与学习使用，未经授权不得外传或用于商业用途。
 *
 * @author Liu qingyang
 * @version 1.0.0
 * @since 2026-04
 */
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hanhong.sycamore")
@MapperScan("com.hanhong.sycamore.**.mapper")
public class SycamoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SycamoreApplication.class, args);
	}

}
