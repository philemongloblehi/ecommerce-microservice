package com.clientui.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public FeignClientConfigurer feignClientConfigurer() {
        return new FeignClientConfigurer() {

            @Override
            public boolean inheritParentConfiguration() {
                return false;
            }
        };
    }

    @Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor(){
        return  new BasicAuthRequestInterceptor("myUsername", "myPassword");
    }
}
