package com.vercel.backend.controller;

import com.vercel.backend.entity.Expense;
import com.vercel.backend.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

/**
 * 経費コントローラー
 */
@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {
    
    @Autowired
    private ExpenseService expenseService;
    
    /**
     * 経費一覧を取得
     * @param userId ユーザーID
     * @return 経費一覧
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getExpenses(@RequestParam String userId) {
        try {
            List<Expense> expenses = expenseService.findByUserId(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("expenses", expenses);
            response.put("success", true);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "経費の取得に失敗しました");
            response.put("success", false);
            response.put("details", e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * 経費一覧を取得（ページネーション付き）
     * @param userId ユーザーID
     * @param page ページ番号（デフォルト: 0）
     * @param size ページサイズ（デフォルト: 20）
     * @return 経費一覧とページ情報
     */
    @GetMapping("/paginated")
    public ResponseEntity<Map<String, Object>> getExpensesWithPagination(
            @RequestParam String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        try {
            Map<String, Object> result = expenseService.findByUserIdWithPagination(userId, page, size);
            result.put("success", true);
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "経費の取得に失敗しました");
            response.put("success", false);
            response.put("details", e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * 経費詳細を取得
     * @param id 経費ID
     * @param userId ユーザーID
     * @return 経費詳細
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getExpense(
            @PathVariable String id, 
            @RequestParam String userId) {
        try {
            Optional<Expense> expense = expenseService.findByIdAndUserId(id, userId);
            
            if (expense.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("expense", expense.get());
                response.put("success", true);
                
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "経費が見つかりません");
                response.put("success", false);
                
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "経費詳細の取得に失敗しました");
            response.put("success", false);
            response.put("details", e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * 経費を作成
     * @param expense 経費
     * @return 作成された経費
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createExpense(@RequestBody Expense expense) {
        try {
            Expense createdExpense = expenseService.create(expense);
            
            Map<String, Object> response = new HashMap<>();
            response.put("expense", createdExpense);
            response.put("message", "経費が正常に登録されました");
            response.put("success", true);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "経費の登録に失敗しました");
            response.put("success", false);
            response.put("details", e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * 経費を更新
     * @param id 経費ID
     * @param expense 経費
     * @return 更新された経費
     */
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateExpense(
            @PathVariable String id, 
            @RequestBody Expense expense) {
        try {
            expense.setId(id);
            Expense updatedExpense = expenseService.update(expense);
            
            Map<String, Object> response = new HashMap<>();
            response.put("expense", updatedExpense);
            response.put("message", "経費が正常に更新されました");
            response.put("success", true);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "経費の更新に失敗しました");
            response.put("success", false);
            response.put("details", e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }
    
    /**
     * 経費を削除
     * @param id 経費ID
     * @param userId ユーザーID
     * @return 削除結果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteExpense(
            @PathVariable String id, 
            @RequestParam String userId) {
        try {
            boolean deleted = expenseService.delete(id, userId);
            
            if (deleted) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "経費が正常に削除されました");
                response.put("success", true);
                
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "経費が見つからないか、削除権限がありません");
                response.put("success", false);
                
                return ResponseEntity.status(404).body(response);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "経費の削除に失敗しました");
            response.put("success", false);
            response.put("details", e.getMessage());
            
            return ResponseEntity.status(500).body(response);
        }
    }
}
