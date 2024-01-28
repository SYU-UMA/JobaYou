package com.jobayour.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// modelmapper 를 사용하기 위해 Bean 별도로 설정해주는 파일 클래스
@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
