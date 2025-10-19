package com.vercel.backend.config;

import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.PostgresDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Doma設定クラス
 * PostgreSQLデータベースとの接続設定を行う
 */
@Configuration
public class DomaConfig {

    /** データソース（Spring Bootが自動設定） */
    @Autowired
    private DataSource dataSource;

    /**
     * Domaの設定Beanを作成する
     * PostgreSQLダイアレクトとデータソースを設定
     * 
     * @return Domaの設定オブジェクト
     */
    @Bean
    public Config domaConfig() {
        return new Config() {
            /**
             * データベースダイアレクトを取得する
             * PostgreSQL用のダイアレクトを返す
             * 
             * @return PostgreSQLダイアレクト
             */
            @Override
            public Dialect getDialect() {
                return new PostgresDialect();
            }

            /**
             * データソースを取得する
             * Spring Bootが設定したデータソースを返す
             * 
             * @return データソース
             */
            @Override
            public DataSource getDataSource() {
                return dataSource;
            }
        };
    }
}
