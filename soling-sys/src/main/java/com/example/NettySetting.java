package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "netty")
@Data
public class NettySetting {
    private Short port;
    private String host;
    private Byte workerGroup;
    private Byte bossGroup;
}
