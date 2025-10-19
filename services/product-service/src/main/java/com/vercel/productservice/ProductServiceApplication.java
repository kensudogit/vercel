package com.vercel.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 商品サービスアプリケーションのメインクラス
 * 商品管理機能を提供するマイクロサービス
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.vercel.productservice")
@EntityScan(basePackages = "com.vercel.productservice.entity")
public class ProductServiceApplication {

    /**
     * アプリケーションのメインメソッド
     * 商品サービスを起動する
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
