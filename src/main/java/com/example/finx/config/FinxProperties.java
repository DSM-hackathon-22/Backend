package com.example.finx.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("finx")
public class FinxProperties {
    private final String naverClientId;
    private final String naverSecretKey;
    private final String stockKey;
}
