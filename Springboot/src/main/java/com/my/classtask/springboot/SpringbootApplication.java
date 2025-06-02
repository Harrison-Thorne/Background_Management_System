package com.my.classtask.springboot;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.my.classtask.springboot.mapper") //扫描指定包 ("com.my.classtask.springboot.mapper") 下的所有接口，并将它们自动注册为 MyBatis 的 Mapper Bean
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
