package com.vercel.backend.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * プロジェクトエンティティクラス
 * プロジェクト情報を表現するエンティティ
 */
public class Project {
    
    /** プロジェクトID（主キー） */
    private String id;
    
    /** プロジェクト名 */
    private String name;
    
    /** プロジェクトの説明 */
    private String description;
    
    /** クライアント名 */
    private String clientName;
    
    /** クライアントのメールアドレス */
    private String clientEmail;
    
    /** プロジェクトのステータス */
    private String status;
    
    /** プロジェクトのカテゴリ */
    private String category;
    
    /** プロジェクト開始日 */
    private LocalDateTime startDate;
    
    /** プロジェクト終了日 */
    private LocalDateTime endDate;
    
    /** プロジェクト予算 */
    private BigDecimal budget;
    
    /** 担当ユーザーID */
    private String userId;
    
    /** 作成日時 */
    private LocalDateTime createdAt;
    
    /** 更新日時 */
    private LocalDateTime updatedAt;
    
    /**
     * デフォルトコンストラクタ
     * JPAやフレームワークで使用される
     */
    public Project() {}
    
    /**
     * プロジェクトエンティティのコンストラクタ
     * 
     * @param id プロジェクトID
     * @param name プロジェクト名
     * @param description プロジェクトの説明
     * @param clientName クライアント名
     * @param clientEmail クライアントのメールアドレス
     * @param status プロジェクトのステータス
     * @param category プロジェクトのカテゴリ
     * @param userId 担当ユーザーID
     */
    public Project(String id, String name, String description, String clientName, 
                   String clientEmail, String status, String category, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.status = status;
        this.category = category;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // ========== Getter/Setter メソッド ==========
    
    /**
     * プロジェクトIDを取得する
     * 
     * @return プロジェクトID
     */
    public String getId() {
        return id;
    }
    
    /**
     * プロジェクトIDを設定する
     * 
     * @param id プロジェクトID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * プロジェクト名を取得する
     * 
     * @return プロジェクト名
     */
    public String getName() {
        return name;
    }
    
    /**
     * プロジェクト名を設定する
     * 
     * @param name プロジェクト名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * プロジェクトの説明を取得する
     * 
     * @return プロジェクトの説明
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * プロジェクトの説明を設定する
     * 
     * @param description プロジェクトの説明
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * クライアント名を取得する
     * 
     * @return クライアント名
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * クライアント名を設定する
     * 
     * @param clientName クライアント名
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /**
     * クライアントのメールアドレスを取得する
     * 
     * @return クライアントのメールアドレス
     */
    public String getClientEmail() {
        return clientEmail;
    }
    
    /**
     * クライアントのメールアドレスを設定する
     * 
     * @param clientEmail クライアントのメールアドレス
     */
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    
    /**
     * プロジェクトのステータスを取得する
     * 
     * @return プロジェクトのステータス
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * プロジェクトのステータスを設定する
     * 
     * @param status プロジェクトのステータス
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * プロジェクトのカテゴリを取得する
     * 
     * @return プロジェクトのカテゴリ
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * プロジェクトのカテゴリを設定する
     * 
     * @param category プロジェクトのカテゴリ
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * プロジェクト開始日を取得する
     * 
     * @return プロジェクト開始日
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    /**
     * プロジェクト開始日を設定する
     * 
     * @param startDate プロジェクト開始日
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    /**
     * プロジェクト終了日を取得する
     * 
     * @return プロジェクト終了日
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    /**
     * プロジェクト終了日を設定する
     * 
     * @param endDate プロジェクト終了日
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    /**
     * プロジェクト予算を取得する
     * 
     * @return プロジェクト予算
     */
    public BigDecimal getBudget() {
        return budget;
    }
    
    /**
     * プロジェクト予算を設定する
     * 
     * @param budget プロジェクト予算
     */
    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
    
    /**
     * 担当ユーザーIDを取得する
     * 
     * @return 担当ユーザーID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * 担当ユーザーIDを設定する
     * 
     * @param userId 担当ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * 作成日時を設定する
     * 
     * @param createdAt 作成日時
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    /**
     * 更新日時を取得する
     * 
     * @return 更新日時
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    /**
     * 更新日時を設定する
     * 
     * @param updatedAt 更新日時
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    /**
     * オブジェクトの文字列表現を返す
     * デバッグやログ出力に使用される
     * 
     * @return プロジェクト情報の文字列表現
     */
    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budget=" + budget +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
