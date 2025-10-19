package com.vercel.backend.service;

import com.vercel.backend.entity.Expense;
import com.vercel.backend.entity.Project;
import com.vercel.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

/**
 * 経費サービスクラス
 */
@Service
public class ExpenseService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 経費詳細を取得（キャッシュ付き）
     * @param expenseId 経費ID
     * @param userId ユーザーID
     * @return 経費詳細
     */
    @Cacheable(value = "expenses", key = "#expenseId + '_' + #userId")
    public Optional<Expense> findByIdAndUserId(String expenseId, String userId) {
        String sql = """
            SELECT 
                e.id, e.project_id, e.user_id, e.category, e.description, 
                e.amount, e.expense_date, e.receipt_url, e.status,
                e.created_at, e.updated_at,
                p.id as project_id, p.name as project_name, p.client_name, p.status as project_status,
                u.id as user_id, u.name as user_name, u.email as user_email
            FROM expenses e
            LEFT JOIN projects p ON e.project_id = p.id
            LEFT JOIN users u ON e.user_id = u.id
            WHERE e.id = ? AND e.user_id = ?
            """;
        
        try {
            Expense expense = jdbcTemplate.queryForObject(sql, new ExpenseRowMapper(), expenseId, userId);
            return Optional.ofNullable(expense);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
    
    /**
     * ユーザーの経費一覧を取得（ページネーション付き）
     * @param userId ユーザーID
     * @param page ページ番号（0から開始）
     * @param size ページサイズ
     * @return 経費一覧とページ情報
     */
    @Cacheable(value = "expenseList", key = "#userId + '_' + #page + '_' + #size")
    public Map<String, Object> findByUserIdWithPagination(String userId, int page, int size) {
        int offset = page * size;
        
        // 総件数を取得
        String countSql = "SELECT COUNT(*) FROM expenses WHERE user_id = ?";
        int totalCount = jdbcTemplate.queryForObject(countSql, Integer.class, userId);
        
        // 経費一覧を取得
        String sql = """
            SELECT 
                e.id, e.project_id, e.user_id, e.category, e.description, 
                e.amount, e.expense_date, e.receipt_url, e.status,
                e.created_at, e.updated_at,
                p.id as project_id, p.name as project_name, p.client_name, p.status as project_status,
                u.id as user_id, u.name as user_name, u.email as user_email
            FROM expenses e
            LEFT JOIN projects p ON e.project_id = p.id
            LEFT JOIN users u ON e.user_id = u.id
            WHERE e.user_id = ?
            ORDER BY e.created_at DESC
            LIMIT ? OFFSET ?
            """;
        
        List<Expense> expenses = jdbcTemplate.query(sql, new ExpenseRowMapper(), userId, size, offset);
        
        Map<String, Object> result = new HashMap<>();
        result.put("expenses", expenses);
        result.put("totalCount", totalCount);
        result.put("page", page);
        result.put("size", size);
        result.put("totalPages", (int) Math.ceil((double) totalCount / size));
        
        return result;
    }
    
    /**
     * ユーザーの経費一覧を取得（従来のメソッド、後方互換性のため）
     * @param userId ユーザーID
     * @return 経費一覧
     */
    @Cacheable(value = "expenseList", key = "#userId + '_all'")
    public List<Expense> findByUserId(String userId) {
        String sql = """
            SELECT 
                e.id, e.project_id, e.user_id, e.category, e.description, 
                e.amount, e.expense_date, e.receipt_url, e.status,
                e.created_at, e.updated_at,
                p.id as project_id, p.name as project_name, p.client_name, p.status as project_status,
                u.id as user_id, u.name as user_name, u.email as user_email
            FROM expenses e
            LEFT JOIN projects p ON e.project_id = p.id
            LEFT JOIN users u ON e.user_id = u.id
            WHERE e.user_id = ?
            ORDER BY e.created_at DESC
            LIMIT 100
            """;
        
        return jdbcTemplate.query(sql, new ExpenseRowMapper(), userId);
    }
    
    /**
     * 経費を作成
     * @param expense 経費
     * @return 作成された経費
     */
    @CacheEvict(value = "expenseList", allEntries = true)
    public Expense create(Expense expense) {
        String sql = """
            INSERT INTO expenses (id, project_id, user_id, category, description, 
                                amount, expense_date, receipt_url, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        
        String id = generateId();
        LocalDateTime now = LocalDateTime.now();
        
        jdbcTemplate.update(sql, 
            id, expense.getProjectId(), expense.getUserId(), expense.getCategory(),
            expense.getDescription(), expense.getAmount(), expense.getExpenseDate(),
            expense.getReceiptUrl(), expense.getStatus(), now, now);
        
        expense.setId(id);
        expense.setCreatedAt(now);
        expense.setUpdatedAt(now);
        
        return expense;
    }
    
    /**
     * 経費を更新
     * @param expense 経費
     * @return 更新された経費
     */
    @CacheEvict(value = {"expenses", "expenseList"}, allEntries = true)
    public Expense update(Expense expense) {
        String sql = """
            UPDATE expenses 
            SET project_id = ?, category = ?, description = ?, amount = ?, 
                expense_date = ?, receipt_url = ?, status = ?, updated_at = ?
            WHERE id = ? AND user_id = ?
            """;
        
        LocalDateTime now = LocalDateTime.now();
        
        int updated = jdbcTemplate.update(sql,
            expense.getProjectId(), expense.getCategory(), expense.getDescription(),
            expense.getAmount(), expense.getExpenseDate(), expense.getReceiptUrl(),
            expense.getStatus(), now, expense.getId(), expense.getUserId());
        
        if (updated > 0) {
            expense.setUpdatedAt(now);
            return expense;
        } else {
            throw new RuntimeException("経費の更新に失敗しました");
        }
    }
    
    /**
     * 経費を削除
     * @param expenseId 経費ID
     * @param userId ユーザーID
     * @return 削除成功フラグ
     */
    @CacheEvict(value = {"expenses", "expenseList"}, allEntries = true)
    public boolean delete(String expenseId, String userId) {
        String sql = "DELETE FROM expenses WHERE id = ? AND user_id = ?";
        
        int deleted = jdbcTemplate.update(sql, expenseId, userId);
        return deleted > 0;
    }
    
    /**
     * IDを生成
     * @return 生成されたID
     */
    private String generateId() {
        return "exp_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
    }
    
    /**
     * 経費のRowMapper
     */
    private static class ExpenseRowMapper implements RowMapper<Expense> {
        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense = new Expense();
            expense.setId(rs.getString("id"));
            expense.setProjectId(rs.getString("project_id"));
            expense.setUserId(rs.getString("user_id"));
            expense.setCategory(rs.getString("category"));
            expense.setDescription(rs.getString("description"));
            expense.setAmount(rs.getBigDecimal("amount"));
            expense.setExpenseDate(rs.getTimestamp("expense_date").toLocalDateTime());
            expense.setReceiptUrl(rs.getString("receipt_url"));
            expense.setStatus(rs.getString("status"));
            expense.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            expense.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            
            // プロジェクト情報を設定
            if (rs.getString("project_id") != null) {
                Project project = new Project();
                project.setId(rs.getString("project_id"));
                project.setName(rs.getString("project_name"));
                project.setClientName(rs.getString("client_name"));
                project.setStatus(rs.getString("project_status"));
                expense.setProject(project);
            }
            
            // ユーザー情報を設定
            if (rs.getString("user_id") != null) {
                User user = new User();
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("user_email"));
                expense.setUser(user);
            }
            
            return expense;
        }
    }
}
