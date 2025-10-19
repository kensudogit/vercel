package com.vercel.backend.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * 経費エンティティクラス
 * プロジェクトに関連する経費情報を表現するエンティティ
 */
public class Expense {
    
    /** 経費ID（主キー） */
    private String id;
    
    /** 関連プロジェクトID */
    private String projectId;
    
    /** 担当ユーザーID */
    private String userId;
    
    /** 経費カテゴリ */
    private String category;
    
    /** 経費の説明 */
    private String description;
    
    /** 経費金額 */
    private BigDecimal amount;
    
    /** 経費発生日 */
    private LocalDateTime expenseDate;
    
    /** 領収書URL */
    private String receiptUrl;
    
    /** 経費ステータス */
    private String status;
    
    /** 作成日時 */
    private LocalDateTime createdAt;
    
    /** 更新日時 */
    private LocalDateTime updatedAt;
    
    // ========== 関連エンティティ ==========
    
    /** 関連プロジェクト */
    private Project project;
    
    /** 関連ユーザー */
    private User user;
    
    /**
     * デフォルトコンストラクタ
     * JPAやフレームワークで使用される
     */
    public Expense() {}
    
    /**
     * 経費エンティティのコンストラクタ
     * 
     * @param id 経費ID
     * @param projectId 関連プロジェクトID
     * @param userId 担当ユーザーID
     * @param category 経費カテゴリ
     * @param description 経費の説明
     * @param amount 経費金額
     * @param expenseDate 経費発生日
     * @param receiptUrl 領収書URL
     * @param status 経費ステータス
     */
    public Expense(String id, String projectId, String userId, String category, 
                   String description, BigDecimal amount, LocalDateTime expenseDate, 
                   String receiptUrl, String status) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.receiptUrl = receiptUrl;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // ========== Getter/Setter メソッド ==========
    
    /**
     * 経費IDを取得する
     * 
     * @return 経費ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * 経費IDを設定する
     * 
     * @param id 経費ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * 関連プロジェクトIDを取得する
     * 
     * @return 関連プロジェクトID
     */
    public String getProjectId() {
        return projectId;
    }
    
    /**
     * 関連プロジェクトIDを設定する
     * 
     * @param projectId 関連プロジェクトID
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
     * 経費カテゴリを取得する
     * 
     * @return 経費カテゴリ
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * 経費カテゴリを設定する
     * 
     * @param category 経費カテゴリ
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * 経費の説明を取得する
     * 
     * @return 経費の説明
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 経費の説明を設定する
     * 
     * @param description 経費の説明
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * 経費金額を取得する
     * 
     * @return 経費金額
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * 経費金額を設定する
     * 
     * @param amount 経費金額
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    /**
     * 経費発生日を取得する
     * 
     * @return 経費発生日
     */
    public LocalDateTime getExpenseDate() {
        return expenseDate;
    }
    
    /**
     * 経費発生日を設定する
     * 
     * @param expenseDate 経費発生日
     */
    public void setExpenseDate(LocalDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }
    
    /**
     * 領収書URLを取得する
     * 
     * @return 領収書URL
     */
    public String getReceiptUrl() {
        return receiptUrl;
    }
    
    /**
     * 領収書URLを設定する
     * 
     * @param receiptUrl 領収書URL
     */
    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }
    
    /**
     * 経費ステータスを取得する
     * 
     * @return 経費ステータス
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 経費ステータスを設定する
     * 
     * @param status 経費ステータス
     */
    public void setStatus(String status) {
        this.status = status;
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
     * 関連プロジェクトを取得する
     * 
     * @return 関連プロジェクト
     */
    public Project getProject() {
        return project;
    }
    
    /**
     * 関連プロジェクトを設定する
     * 
     * @param project 関連プロジェクト
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
    /**
     * 関連ユーザーを取得する
     * 
     * @return 関連ユーザー
     */
    public User getUser() {
        return user;
    }
    
    /**
     * 関連ユーザーを設定する
     * 
     * @param user 関連ユーザー
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * オブジェクトの文字列表現を返す
     * デバッグやログ出力に使用される
     * 
     * @return 経費情報の文字列表現
     */
    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", expenseDate=" + expenseDate +
                ", receiptUrl='" + receiptUrl + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
