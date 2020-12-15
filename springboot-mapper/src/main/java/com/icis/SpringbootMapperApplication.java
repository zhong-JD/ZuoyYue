package com.icis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//开启包扫描
@MapperScan(basePackages = "com.icis.mapper")
public class SpringbootMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMapperApplication.class, args);
	}

}
