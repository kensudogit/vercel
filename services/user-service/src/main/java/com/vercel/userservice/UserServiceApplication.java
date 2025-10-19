package com.vercel.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ユーザーサービスアプリケーションのメインクラス
 * ユーザー管理機能を提供するマイクロサービス
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.vercel.userservice")
@EntityScan(basePackages = "com.vercel.userservice.entity")
public class UserServiceApplication {

    /**
     * アプリケーションのメインメソッド
     * ユーザーサービスを起動する
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
