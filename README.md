# IT Consultant Management — Next.js / Prisma / PostgreSQL

> **Professional Services Management** — ITコンサルタント向けに、案件・請求書・経費・売上レポート・ダッシュボードを統合した業務管理Webアプリケーションです。
>
> **Stack:** Next.js 15 · React · TypeScript · Prisma · PostgreSQL · Redis · Docker

## Architecture

```text
Consultant / Administrator
          │
          ▼
     Next.js UI
          │
          ▼
 Next.js API / Prisma
      │         │
      ▼         ▼
 PostgreSQL    Redis
```

## Business Capabilities

- プロジェクト・案件管理
- 請求書管理
- 経費管理
- 売上・収益レポート
- 管理ダッシュボード
- ユーザー認証・認可

## Engineering Focus

- Next.js App Routerを利用したフルスタック構成
- Prismaによる型安全なデータアクセス
- PostgreSQLによる業務データ管理
- Redisを利用できるキャッシュ構成
- Docker / Docker Composeによる環境再現
- Professional Services業務のデジタル化

## Portfolio Context

This repository represents the **Professional Services / Business Management** track of my portfolio. The repository name reflects its original deployment target, while the implemented product is an IT consultant management system.

## 🚀 技術スタック

- **Frontend**: Next.js 15, React 18, TypeScript, Tailwind CSS
- **Backend**: Next.js API Routes, Prisma ORM
- **Database**: PostgreSQL
- **Cache**: Redis
- **Container**: Docker & Docker Compose

## 📋 機能

- ユーザー認証・認可
- プロジェクト管理
- 請求書管理
- 経費管理
- 売上レポート
- ダッシュボード

## 🛠️ セットアップ

### 前提条件

- Docker & Docker Compose
- Node.js 18+ (ローカル開発用)

### 1. リポジトリのクローン

```bash
git clone <repository-url>
cd vercel
```

### 2. 環境変数の設定

```bash
# フロントエンドディレクトリに移動
cd frontend

# 環境変数ファイルを作成
cp .env.example .env.local
```

`.env.local`ファイルを編集して、必要な環境変数を設定してください：

```env
DATABASE_URL="${DATABASE_URL}"
JWT_SECRET="${JWT_SECRET}"
NODE_ENV="development"
NEXT_PUBLIC_APP_URL="http://localhost:3000"
REDIS_URL="redis://localhost:6379"
```

### 3. Docker環境の起動

#### 開発環境（データベースのみ）

```bash
# プロジェクトルートで実行
docker-compose -f docker-compose.dev.yml up -d
```

#### 本番環境（フルスタック）

```bash
# プロジェクトルートで実行
docker-compose up -d
```

### 4. データベースの初期化

```bash
cd frontend
npx prisma migrate dev
npx prisma generate
```

### 5. アプリケーションの起動

#### ローカル開発

```bash
cd frontend
npm install
npm run dev
```

#### Docker環境

```bash
# 既にdocker-compose up -dを実行している場合
# アプリケーションは自動的に起動します
```

## 🌐 アクセス

- **アプリケーション**: http://localhost:3000
- **PostgreSQL**: localhost:5432
- **Redis**: localhost:6379

## 📊 デフォルトユーザー

データベース初期化後、以下のテストユーザーが作成されます：

- **Email**: admin@example.com
- **Password**: `<set-a-secure-password>`
- **Role**: ADMIN

## 🔧 開発コマンド

```bash
# 依存関係のインストール
npm install

# 開発サーバーの起動
npm run dev

# ビルド
npm run build

# 本番サーバーの起動
npm start

# テストの実行
npm test

# Prismaクライアントの生成
npx prisma generate

# データベースマイグレーション
npx prisma migrate dev

# Prisma Studio（データベースGUI）
npx prisma studio
```

## 🐳 Dockerコマンド

```bash
# サービスの起動
docker-compose up -d

# サービスの停止
docker-compose down

# ログの確認
docker-compose logs -f

# 特定のサービスのログ
docker-compose logs -f frontend

# データベースのリセット
docker-compose down -v
docker-compose up -d
```

## 📁 プロジェクト構造

```
vercel/
├── frontend/                 # Next.jsアプリケーション
│   ├── src/
│   │   ├── app/             # App Router
│   │   ├── components/      # Reactコンポーネント
│   │   ├── lib/            # ユーティリティ
│   │   └── types/          # TypeScript型定義
│   ├── prisma/             # Prismaスキーマ
│   └── Dockerfile          # Docker設定
├── docker/
│   └── init-scripts/      # データベース初期化スクリプト
├── docker-compose.yml     # 本番環境用
├── docker-compose.dev.yml # 開発環境用
└── README.md
```

## 🚀 デプロイ

### Vercel

1. Vercelアカウントにログイン
2. プロジェクトをインポート
3. 環境変数を設定
4. デプロイ

### Docker

```bash
# 本番ビルド
docker-compose -f docker-compose.yml up -d --build
```

## 🤝 コントリビューション

1. リポジトリをフォーク
2. フィーチャーブランチを作成
3. 変更をコミット
4. プルリクエストを作成

## 📄 ライセンス

このプロジェクトはMITライセンスの下で公開されています。"# vercel" 


### Security note

Do not commit real credentials or secrets to this repository. Configure database passwords and JWT secrets through environment variables or the deployment platform's secret management facility.
