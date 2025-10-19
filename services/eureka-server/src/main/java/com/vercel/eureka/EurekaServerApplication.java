package com.vercel.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eurekaサーバーアプリケーションのメインクラス
 * マイクロサービス間のサービスディスカバリを提供する
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    /**
     * アプリケーションのメインメソッド
     * Eurekaサーバーを起動する
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
