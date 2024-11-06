package ru.savin.videostreamingauth;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${jasypt.password}")
    private String jasyptPassword;

    @Bean
    public AES256TextEncryptor getEncryptor() {
        AES256TextEncryptor basicTextEncryptor = new AES256TextEncryptor();
        basicTextEncryptor.setPasswordCharArray(jasyptPassword.toCharArray());
        return basicTextEncryptor;
    }
}
