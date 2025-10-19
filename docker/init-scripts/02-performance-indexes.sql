-- パフォーマンス最適化のためのインデックス作成スクリプト

-- 経費テーブルのインデックス
CREATE INDEX IF NOT EXISTS idx_expenses_user_id ON expenses(user_id);
CREATE INDEX IF NOT EXISTS idx_expenses_project_id ON expenses(project_id);
CREATE INDEX IF NOT EXISTS idx_expenses_category ON expenses(category);
CREATE INDEX IF NOT EXISTS idx_expenses_expense_date ON expenses(expense_date);
CREATE INDEX IF NOT EXISTS idx_expenses_created_at ON expenses(created_at);
CREATE INDEX IF NOT EXISTS idx_expenses_status ON expenses(status);

-- 複合インデックス（よく使われるクエリパターン用）
CREATE INDEX IF NOT EXISTS idx_expenses_user_created ON expenses(user_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_expenses_user_category ON expenses(user_id, category);
CREATE INDEX IF NOT EXISTS idx_expenses_project_created ON expenses(project_id, created_at DESC);

-- プロジェクトテーブルのインデックス
CREATE INDEX IF NOT EXISTS idx_projects_user_id ON projects(user_id);
CREATE INDEX IF NOT EXISTS idx_projects_status ON projects(status);
CREATE INDEX IF NOT EXISTS idx_projects_category ON projects(category);
CREATE INDEX IF NOT EXISTS idx_projects_created_at ON projects(created_at);

-- ユーザーテーブルのインデックス
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);

-- 請求書テーブルのインデックス
CREATE INDEX IF NOT EXISTS idx_invoices_user_id ON invoices(user_id);
CREATE INDEX IF NOT EXISTS idx_invoices_project_id ON invoices(project_id);
CREATE INDEX IF NOT EXISTS idx_invoices_status ON invoices(status);
CREATE INDEX IF NOT EXISTS idx_invoices_due_date ON invoices(due_date);
CREATE INDEX IF NOT EXISTS idx_invoices_issue_date ON invoices(issue_date);

-- 売上レポートテーブルのインデックス
CREATE INDEX IF NOT EXISTS idx_sales_reports_month_year ON sales_reports(month, year);

-- 統計情報の更新（PostgreSQL）
ANALYZE expenses;
ANALYZE projects;
ANALYZE users;
ANALYZE invoices;
ANALYZE sales_reports;

-- パフォーマンス監視用のビュー
CREATE OR REPLACE VIEW expense_performance_stats AS
SELECT 
    COUNT(*) as total_expenses,
    COUNT(DISTINCT user_id) as unique_users,
    COUNT(DISTINCT project_id) as unique_projects,
    AVG(amount) as avg_amount,
    SUM(amount) as total_amount,
    MIN(created_at) as earliest_expense,
    MAX(created_at) as latest_expense
FROM expenses;

-- よく使われるクエリのためのマテリアライズドビュー（PostgreSQL 9.3+）
CREATE MATERIALIZED VIEW IF NOT EXISTS user_expense_summary AS
SELECT 
    u.id as user_id,
    u.name as user_name,
    COUNT(e.id) as expense_count,
    SUM(e.amount) as total_amount,
    AVG(e.amount) as avg_amount,
    MAX(e.created_at) as last_expense_date
FROM users u
LEFT JOIN expenses e ON u.id = e.user_id
GROUP BY u.id, u.name;

-- マテリアライズドビューのインデックス
CREATE INDEX IF NOT EXISTS idx_user_expense_summary_user_id ON user_expense_summary(user_id);

-- マテリアライズドビューの更新関数
CREATE OR REPLACE FUNCTION refresh_user_expense_summary()
RETURNS void AS $$
BEGIN
    REFRESH MATERIALIZED VIEW user_expense_summary;
END;
$$ LANGUAGE plpgsql;

-- パーティショニング（大量データの場合）
-- 経費テーブルを月単位でパーティション化
-- CREATE TABLE expenses_y2024m01 PARTITION OF expenses
-- FOR VALUES FROM ('2024-01-01') TO ('2024-02-01');

-- 接続プール設定の確認
-- SHOW max_connections;
-- SHOW shared_buffers;
-- SHOW effective_cache_size;
-- SHOW work_mem;
-- SHOW maintenance_work_mem;
