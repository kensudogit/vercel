package com.vercel.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * APIゲートウェイアプリケーションのメインクラス
 * マイクロサービスへの統一エントリーポイントを提供する
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.vercel.apigateway")
public class ApiGatewayApplication {

    /**
     * アプリケーションのメインメソッド
     * APIゲートウェイを起動する
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
