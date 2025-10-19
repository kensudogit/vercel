package com.vercel.userservice.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;

import java.time.LocalDateTime;

/**
 * ユーザーエンティティクラス（ユーザーサービス用）
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
    private final String username;
    
    /** メールアドレス */
    private final String email;
    
    /** パスワード */
    private final String password;
    
    /** 名 */
    private final String firstName;
    
    /** 姓 */
    private final String lastName;
    
    /** ロール */
    private final String role;
    
    /** アクティブフラグ */
    private final Boolean active;
    
    /** 作成日時 */
    private final LocalDateTime createdAt;
    
    /** 更新日時 */
    private final LocalDateTime updatedAt;
    
    /**
     * ユーザーエンティティのコンストラクタ
     * 
     * @param id ユーザーID
     * @param username ユーザー名
     * @param email メールアドレス
     * @param password パスワード
     * @param firstName 名
     * @param lastName 姓
     * @param role ロール
     * @param active アクティブフラグ
     * @param createdAt 作成日時
     * @param updatedAt 更新日時
     */
    public User(Long id, String username, String email, String password, 
                String firstName, String lastName, String role, Boolean active,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    /** ユーザーIDを取得する */
    public Long getId() { return id; }
    
    /** ユーザー名を取得する */
    public String getUsername() { return username; }
    
    /** メールアドレスを取得する */
    public String getEmail() { return email; }
    
    /** パスワードを取得する */
    public String getPassword() { return password; }
    
    /** 名を取得する */
    public String getFirstName() { return firstName; }
    
    /** 姓を取得する */
    public String getLastName() { return lastName; }
    
    /** ロールを取得する */
    public String getRole() { return role; }
    
    /** アクティブフラグを取得する */
    public Boolean getActive() { return active; }
    
    /** 作成日時を取得する */
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    /** 更新日時を取得する */
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
