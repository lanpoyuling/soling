package com.example;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * mongo数据库连接信息
 */
@Component
@ConfigurationProperties(prefix = "mongodb")
@ToString
@Data
public class MongoSetting {
    /**
     * mongo端口
     */
    private Short port;
    /**
     * mongoIP
     */
    private String host;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private char[] password;
    /**
     * 数据库
     */
    private String database;
}
