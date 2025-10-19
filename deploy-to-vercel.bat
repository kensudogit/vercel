@echo off
REM Vercel本番環境デプロイスクリプト (Windows版)
REM 使用方法: deploy-to-vercel.bat

setlocal enabledelayedexpansion

echo 🚀 Vercel本番環境デプロイを開始します...

REM 環境チェック
:check_requirements
echo [INFO] 環境要件をチェックしています...

REM Node.js チェック
node --version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Node.js がインストールされていません
    exit /b 1
)

REM npm チェック
npm --version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] npm がインストールされていません
    exit /b 1
)

REM Vercel CLI チェック
vercel --version >nul 2>&1
if errorlevel 1 (
    echo [WARN] Vercel CLI がインストールされていません。インストールします...
    npm install -g vercel
)

echo [INFO] 環境要件チェック完了

REM フロントエンドディレクトリに移動
cd frontend

REM 依存関係のインストール
:install_dependencies
echo [INFO] 依存関係をインストールしています...
call npm ci
if errorlevel 1 (
    echo [ERROR] 依存関係のインストールに失敗しました
    exit /b 1
)
echo [INFO] 依存関係のインストール完了

REM ビルドテスト
:build_test
echo [INFO] ビルドテストを実行しています...
call npm run build
if errorlevel 1 (
    echo [ERROR] ビルドテストに失敗しました
    exit /b 1
)
echo [INFO] ビルドテスト完了

REM 環境変数の確認
:check_env_vars
echo [INFO] 環境変数を確認しています...

if not exist ".env.production" (
    echo [WARN] .env.production ファイルが見つかりません
    echo [INFO] env.production.example を参考に .env.production を作成してください
    copy env.production.example .env.production
    echo [WARN] ⚠️  .env.production ファイルを編集してから再実行してください
    exit /b 1
)

echo [INFO] 環境変数チェック完了

REM Vercelにログイン
:vercel_login
echo [INFO] Vercelにログインしています...
call vercel login
if errorlevel 1 (
    echo [ERROR] Vercelログインに失敗しました
    exit /b 1
)

REM プロジェクトの設定
:setup_project
echo [INFO] Vercelプロジェクトを設定しています...

REM プロジェクトが存在しない場合は作成
vercel project ls | findstr "vercel-expense-management" >nul
if errorlevel 1 (
    echo [INFO] 新しいプロジェクトを作成しています...
    call vercel project add vercel-expense-management
)

echo [INFO] プロジェクト設定完了

REM 環境変数をVercelに設定
:set_env_vars
echo [INFO] 環境変数をVercelに設定しています...

REM .env.productionから環境変数を読み込んで設定
for /f "tokens=1,2 delims==" %%a in (.env.production) do (
    if not "%%a"=="" (
        echo [INFO] 設定中: %%a
        echo %%b | vercel env add %%a production
    )
)

echo [INFO] 環境変数設定完了

REM デプロイ実行
:deploy
echo [INFO] Vercelにデプロイしています...

REM 本番環境にデプロイ
call vercel --prod --yes
if errorlevel 1 (
    echo [ERROR] デプロイに失敗しました
    exit /b 1
)

echo [INFO] デプロイ完了！

REM デプロイ後の確認
:post_deploy_check
echo [INFO] デプロイ後の確認を行っています...

REM デプロイURLを取得
for /f "tokens=2" %%a in ('vercel ls ^| findstr "vercel-expense-management"') do set DEPLOY_URL=%%a

if not "%DEPLOY_URL%"=="" (
    echo [INFO] デプロイURL: https://%DEPLOY_URL%
    
    REM ヘルスチェック
    echo [INFO] ヘルスチェックを実行しています...
    timeout /t 10 /nobreak >nul
    
    curl -f -s "https://%DEPLOY_URL%/api/health" >nul 2>&1
    if errorlevel 1 (
        echo [WARN] ⚠️  ヘルスチェック失敗 - アプリケーションの起動を待っています...
    ) else (
        echo [INFO] ✅ ヘルスチェック成功
    )
) else (
    echo [WARN] デプロイURLを取得できませんでした
)

echo [INFO] 🎉 デプロイが完了しました！
echo [INFO] アプリケーションURL: https://vercel-expense-management.vercel.app

pause
