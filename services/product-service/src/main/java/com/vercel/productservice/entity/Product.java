package com.vercel.productservice.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品エンティティクラス
 * 商品情報を表現する不変オブジェクト
 */
@Entity(immutable = true)
@Table(name = "products")
public class Product {
    
    /** 商品ID（主キー、自動生成） */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    
    /** 商品名 */
    private final String name;
    
    /** 商品説明 */
    private final String description;
    
    /** 価格 */
    private final BigDecimal price;
    
    /** 在庫数 */
    private final Integer stock;
    
    /** カテゴリ */
    private final String category;
    
    /** SKU（商品コード） */
    private final String sku;
    
    /** アクティブフラグ */
    private final Boolean active;
    
    /** 作成日時 */
    private final LocalDateTime createdAt;
    
    /** 更新日時 */
    private final LocalDateTime updatedAt;
    
    /**
     * 商品エンティティのコンストラクタ
     * 
     * @param id 商品ID
     * @param name 商品名
     * @param description 商品説明
     * @param price 価格
     * @param stock 在庫数
     * @param category カテゴリ
     * @param sku SKU（商品コード）
     * @param active アクティブフラグ
     * @param createdAt 作成日時
     * @param updatedAt 更新日時
     */
    public Product(Long id, String name, String description, BigDecimal price, 
                   Integer stock, String category, String sku, Boolean active,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.sku = sku;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    /** 商品IDを取得する */
    public Long getId() { return id; }
    
    /** 商品名を取得する */
    public String getName() { return name; }
    
    /** 商品説明を取得する */
    public String getDescription() { return description; }
    
    /** 価格を取得する */
    public BigDecimal getPrice() { return price; }
    
    /** 在庫数を取得する */
    public Integer getStock() { return stock; }
    
    /** カテゴリを取得する */
    public String getCategory() { return category; }
    
    /** SKU（商品コード）を取得する */
    public String getSku() { return sku; }
    
    /** アクティブフラグを取得する */
    public Boolean getActive() { return active; }
    
    /** 作成日時を取得する */
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    /** 更新日時を取得する */
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
