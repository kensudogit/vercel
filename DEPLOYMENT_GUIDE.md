# Vercel本番環境デプロイガイド

このガイドでは、Vercel経費管理システムをVercelサーバーに本番環境としてデプロイする手順を説明します。

## 📋 前提条件

- Node.js 18以上
- npm または yarn
- Vercel CLI
- GitHubアカウント（推奨）

## 🚀 デプロイ手順

### 1. 環境準備

```bash
# Vercel CLIをインストール
npm install -g vercel

# プロジェクトディレクトリに移動
cd devlop/vercel/frontend
```

### 2. 環境変数の設定

```bash
# 環境変数ファイルをコピー
cp env.production.example .env.production

# .env.productionを編集
# 以下の値を実際の値に変更してください：
# - POSTGRES_PRISMA_URL: 本番データベースURL
# - JWT_SECRET: 強力なJWT秘密鍵
# - NEXTAUTH_SECRET: NextAuth秘密鍵
```

### 3. 自動デプロイ（推奨）

#### Linux/Mac:
```bash
# 実行権限を付与
chmod +x ../deploy-to-vercel.sh

# デプロイ実行
../deploy-to-vercel.sh
```

#### Windows:
```cmd
# デプロイ実行
..\deploy-to-vercel.bat
```

### 4. 手動デプロイ

```bash
# Vercelにログイン
vercel login

# プロジェクトを初期化（初回のみ）
vercel

# 環境変数を設定
vercel env add POSTGRES_PRISMA_URL production
vercel env add JWT_SECRET production
vercel env add NEXTAUTH_SECRET production
# ... その他の環境変数

# 本番環境にデプロイ
vercel --prod
```

## 🔧 環境変数設定

以下の環境変数をVercelダッシュボードまたはCLIで設定してください：

### 必須環境変数

| 変数名 | 説明 | 例 |
|--------|------|-----|
| `POSTGRES_PRISMA_URL` | PostgreSQL接続URL | `postgresql://user:pass@host:5432/db` |
| `JWT_SECRET` | JWT秘密鍵 | `your-super-secret-key` |
| `NEXTAUTH_SECRET` | NextAuth秘密鍵 | `your-nextauth-secret` |
| `NEXTAUTH_URL` | アプリケーションURL | `https://your-app.vercel.app` |

### オプション環境変数

| 変数名 | 説明 | デフォルト |
|--------|------|-----------|
| `NODE_ENV` | 実行環境 | `production` |
| `LOG_LEVEL` | ログレベル | `info` |
| `CACHE_TTL` | キャッシュ時間（秒） | `600` |

## 🌐 ドメイン設定

### カスタムドメイン

1. Vercelダッシュボードでプロジェクトを開く
2. Settings → Domains
3. カスタムドメインを追加
4. DNS設定でCNAMEレコードを設定

### SSL証明書

Vercelは自動的にSSL証明書を発行・更新します。

## 📊 監視・分析

### Vercel Analytics

```bash
# Analyticsを有効化
vercel analytics enable
```

### パフォーマンス監視

- Vercelダッシュボードの「Analytics」タブ
- 「Speed Insights」でパフォーマンス監視
- 「Web Vitals」でユーザー体験指標を確認

## 🔄 CI/CD設定

### GitHub連携

1. GitHubリポジトリにコードをプッシュ
2. VercelダッシュボードでGitHub連携を設定
3. 自動デプロイが有効化される

### 環境別デプロイ

- `main`ブランチ → 本番環境
- `develop`ブランチ → ステージング環境
- `feature/*`ブランチ → プレビュー環境

## 🛠️ トラブルシューティング

### よくある問題

#### 1. ビルドエラー

```bash
# ローカルでビルドテスト
npm run build

# 依存関係を再インストール
rm -rf node_modules package-lock.json
npm install
```

#### 2. 環境変数エラー

```bash
# 環境変数を確認
vercel env ls

# 環境変数を再設定
vercel env add VARIABLE_NAME production
```

#### 3. データベース接続エラー

- データベースURLを確認
- ネットワークアクセス設定を確認
- SSL証明書の設定を確認

### ログ確認

```bash
# デプロイログを確認
vercel logs

# リアルタイムログ
vercel logs --follow
```

## 📈 パフォーマンス最適化

### 既に実装済みの最適化

- ✅ 画像最適化（WebP/AVIF）
- ✅ コード分割
- ✅ キャッシュ設定
- ✅ CDN配信
- ✅ 圧縮（gzip）

### 追加最適化

```bash
# Bundle Analyzerでバンドルサイズを確認
npm run analyze

# Lighthouseでパフォーマンス測定
npm run lighthouse
```

## 🔒 セキュリティ設定

### 既に実装済みのセキュリティ

- ✅ HTTPS強制
- ✅ セキュリティヘッダー
- ✅ XSS保護
- ✅ CSRF保護
- ✅ レート制限

### 追加セキュリティ

```bash
# セキュリティスキャン
npm audit

# 依存関係の脆弱性チェック
npm audit fix
```

## 📞 サポート

### ドキュメント

- [Vercel Documentation](https://vercel.com/docs)
- [Next.js Documentation](https://nextjs.org/docs)

### コミュニティ

- [Vercel Discord](https://vercel.com/discord)
- [Next.js GitHub](https://github.com/vercel/next.js)

---

## 🎉 デプロイ完了！

デプロイが完了すると、以下のURLでアプリケーションにアクセスできます：

- **本番URL**: `https://your-app-name.vercel.app`
- **管理画面**: `https://vercel.com/dashboard`

お疲れ様でした！🚀
