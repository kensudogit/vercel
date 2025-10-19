package com.vercel.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Vercelバックエンドアプリケーションのメインクラス
 * Spring Bootアプリケーションのエントリーポイント
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.vercel.backend")
@EntityScan(basePackages = "com.vercel.backend.entity")
public class VercelBackendApplication {

    /**
     * アプリケーションのメインメソッド
     * Spring Bootアプリケーションを起動する
     * 
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        SpringApplication.run(VercelBackendApplication.class, args);
    }
}
