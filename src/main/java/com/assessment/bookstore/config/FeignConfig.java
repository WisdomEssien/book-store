package com.assessment.bookstore.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.assessment.bookstore.*")
public class FeignConfig {
}
