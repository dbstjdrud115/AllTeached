package com.example.myPersonalSetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//SpringSecurity 설치 시, 기본으로 표시되는 login화면 비표시.
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class MyPersonalSettingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPersonalSettingApplication.class, args);
	}

}
