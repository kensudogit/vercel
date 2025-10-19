-- Create database if not exists
SELECT 'CREATE DATABASE postgres'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'postgres')\gexec

-- Connect to the database
\c postgres;

-- Create extensions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id TEXT PRIMARY KEY DEFAULT gen_random_uuid()::text,
    email TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    role TEXT DEFAULT 'USER',
    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create projects table
CREATE TABLE IF NOT EXISTS projects (
    id TEXT PRIMARY KEY DEFAULT gen_random_uuid()::text,
    name TEXT NOT NULL,
    description TEXT,
    "clientName" TEXT NOT NULL,
    "clientEmail" TEXT,
    status TEXT NOT NULL,
    category TEXT NOT NULL,
    "startDate" TIMESTAMP,
    "endDate" TIMESTAMP,
    budget DECIMAL,
    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "userId" TEXT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

-- Create invoices table
CREATE TABLE IF NOT EXISTS invoices (
    id TEXT PRIMARY KEY DEFAULT gen_random_uuid()::text,
    "invoiceNumber" TEXT UNIQUE NOT NULL,
    "projectId" TEXT NOT NULL REFERENCES projects(id) ON DELETE CASCADE,
    "userId" TEXT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    amount DECIMAL NOT NULL,
    "taxRate" DECIMAL DEFAULT 0.1,
    status TEXT NOT NULL,
    "issueDate" TIMESTAMP NOT NULL,
    "dueDate" TIMESTAMP NOT NULL,
    "paidDate" TIMESTAMP,
    notes TEXT,
    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create expenses table
CREATE TABLE IF NOT EXISTS expenses (
    id TEXT PRIMARY KEY DEFAULT gen_random_uuid()::text,
    "projectId" TEXT REFERENCES projects(id) ON DELETE SET NULL,
    "userId" TEXT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    category TEXT NOT NULL,
    description TEXT NOT NULL,
    amount DECIMAL NOT NULL,
    date TIMESTAMP NOT NULL,
    "receiptUrl" TEXT,
    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create sales_reports table
CREATE TABLE IF NOT EXISTS sales_reports (
    id TEXT PRIMARY KEY DEFAULT gen_random_uuid()::text,
    month INTEGER NOT NULL,
    year INTEGER NOT NULL,
    "totalSales" DECIMAL NOT NULL,
    "totalExpenses" DECIMAL NOT NULL,
    "netProfit" DECIMAL NOT NULL,
    "createdAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updatedAt" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(month, year)
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_projects_user_id ON projects("userId");
CREATE INDEX IF NOT EXISTS idx_invoices_project_id ON invoices("projectId");
CREATE INDEX IF NOT EXISTS idx_invoices_user_id ON invoices("userId");
CREATE INDEX IF NOT EXISTS idx_expenses_project_id ON expenses("projectId");
CREATE INDEX IF NOT EXISTS idx_expenses_user_id ON expenses("userId");
CREATE INDEX IF NOT EXISTS idx_sales_reports_month_year ON sales_reports(month, year);

