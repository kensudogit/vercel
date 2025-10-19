package com.vercel.backend.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;

import java.time.LocalDateTime;

/**
 * ユーザーエンティティクラス
 * ユーザー情報を表現する不変オブジェクト
 */
@Entity(immutable = true)
@Table(name = "users")
public class User {
    
    /** ユーザーID（主キー、自動生成） */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    
    /** ユーザー名 */
    private final String name;
    
    /** メールアドレス */
    private final String email;
    
    /** 作成日時 */
    private final LocalDateTime createdAt;
    
    /** 更新日時 */
    private final LocalDateTime updatedAt;
    
    /**
     * ユーザーエンティティのコンストラクタ
     * 
     * @param id ユーザーID
     * @param name ユーザー名
     * @param email メールアドレス
     * @param createdAt 作成日時
     * @param updatedAt 更新日時
     */
    public User(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    /**
     * ユーザーIDを取得する
     * 
     * @return ユーザーID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * ユーザー名を取得する
     * 
     * @return ユーザー名
     */
    public String getName() {
        return name;
    }
    
    /**
     * メールアドレスを取得する
     * 
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 作成日時を取得する
     * 
     * @return 作成日時
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    /**
     * 更新日時を取得する
     * 
     * @return 更新日時
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
