package com.example;

import com.google.common.collect.Lists;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Configuration
@Slf4j
@EnableReactiveMongoRepositories
public class MongoConfiguration {
    @Resource
    private MongoSetting mongoSetting;

    @PostConstruct
    public void init() {
        log.info("mongoConnect:{}", mongoSetting);
    }

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {

        List<ServerAddress> hosts = Lists.newArrayList(new ServerAddress(mongoSetting.getHost(),
                mongoSetting.getPort()));


        MongoCredential credential = MongoCredential.createCredential(mongoSetting.getUserName(),
                mongoSetting.getDatabase(),
                mongoSetting.getPassword());
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyToClusterSettings(builder -> builder.hosts(hosts))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);

        return new SimpleReactiveMongoDatabaseFactory(mongoClient, mongoSetting.getDatabase());
    }


    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory());
    }
}
