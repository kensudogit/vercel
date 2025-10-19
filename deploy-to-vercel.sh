#!/bin/bash

# Vercel本番環境デプロイスクリプト
# 使用方法: ./deploy-to-vercel.sh

set -e

echo "🚀 Vercel本番環境デプロイを開始します..."

# 色付きログ関数
log_info() {
    echo -e "\033[32m[INFO]\033[0m $1"
}

log_warn() {
    echo -e "\033[33m[WARN]\033[0m $1"
}

log_error() {
    echo -e "\033[31m[ERROR]\033[0m $1"
}

# 環境チェック
check_requirements() {
    log_info "環境要件をチェックしています..."
    
    # Node.js チェック
    if ! command -v node &> /dev/null; then
        log_error "Node.js がインストールされていません"
        exit 1
    fi
    
    # npm チェック
    if ! command -v npm &> /dev/null; then
        log_error "npm がインストールされていません"
        exit 1
    fi
    
    # Vercel CLI チェック
    if ! command -v vercel &> /dev/null; then
        log_warn "Vercel CLI がインストールされていません。インストールします..."
        npm install -g vercel
    fi
    
    log_info "環境要件チェック完了"
}

# フロントエンドディレクトリに移動
cd frontend

# 依存関係のインストール
install_dependencies() {
    log_info "依存関係をインストールしています..."
    npm ci
    log_info "依存関係のインストール完了"
}

# ビルドテスト
build_test() {
    log_info "ビルドテストを実行しています..."
    npm run build
    log_info "ビルドテスト完了"
}

# 環境変数の確認
check_env_vars() {
    log_info "環境変数を確認しています..."
    
    if [ ! -f ".env.production" ]; then
        log_warn ".env.production ファイルが見つかりません"
        log_info "env.production.example を参考に .env.production を作成してください"
        cp env.production.example .env.production
        log_warn "⚠️  .env.production ファイルを編集してから再実行してください"
        exit 1
    fi
    
    log_info "環境変数チェック完了"
}

# Vercelにログイン
vercel_login() {
    log_info "Vercelにログインしています..."
    vercel login
}

# プロジェクトの設定
setup_project() {
    log_info "Vercelプロジェクトを設定しています..."
    
    # プロジェクトが存在しない場合は作成
    if ! vercel project ls | grep -q "vercel-expense-management"; then
        log_info "新しいプロジェクトを作成しています..."
        vercel project add vercel-expense-management
    fi
    
    log_info "プロジェクト設定完了"
}

# 環境変数をVercelに設定
set_env_vars() {
    log_info "環境変数をVercelに設定しています..."
    
    # .env.productionから環境変数を読み込んで設定
    while IFS= read -r line; do
        if [[ $line =~ ^[A-Z_]+= ]]; then
            key=$(echo $line | cut -d'=' -f1)
            value=$(echo $line | cut -d'=' -f2-)
            log_info "設定中: $key"
            vercel env add $key production <<< $value
        fi
    done < .env.production
    
    log_info "環境変数設定完了"
}

# デプロイ実行
deploy() {
    log_info "Vercelにデプロイしています..."
    
    # 本番環境にデプロイ
    vercel --prod --yes
    
    log_info "デプロイ完了！"
}

# デプロイ後の確認
post_deploy_check() {
    log_info "デプロイ後の確認を行っています..."
    
    # デプロイURLを取得
    DEPLOY_URL=$(vercel ls | grep "vercel-expense-management" | head -1 | awk '{print $2}')
    
    if [ -n "$DEPLOY_URL" ]; then
        log_info "デプロイURL: https://$DEPLOY_URL"
        
        # ヘルスチェック
        log_info "ヘルスチェックを実行しています..."
        sleep 10
        
        if curl -f -s "https://$DEPLOY_URL/api/health" > /dev/null; then
            log_info "✅ ヘルスチェック成功"
        else
            log_warn "⚠️  ヘルスチェック失敗 - アプリケーションの起動を待っています..."
        fi
    else
        log_warn "デプロイURLを取得できませんでした"
    fi
}

# メイン実行
main() {
    log_info "=== Vercel本番環境デプロイスクリプト ==="
    
    check_requirements
    install_dependencies
    build_test
    check_env_vars
    vercel_login
    setup_project
    set_env_vars
    deploy
    post_deploy_check
    
    log_info "🎉 デプロイが完了しました！"
    log_info "アプリケーションURL: https://vercel-expense-management.vercel.app"
}

# エラーハンドリング
trap 'log_error "デプロイ中にエラーが発生しました。ログを確認してください。"; exit 1' ERR

# スクリプト実行
main "$@"
