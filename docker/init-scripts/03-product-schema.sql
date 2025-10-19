-- Product Service Database Schema
\c product_db;

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    stock INTEGER DEFAULT 0,
    category VARCHAR(50),
    sku VARCHAR(50) UNIQUE NOT NULL,
    active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes
CREATE INDEX idx_products_name ON products(name);
CREATE INDEX idx_products_category ON products(category);
CREATE INDEX idx_products_sku ON products(sku);
CREATE INDEX idx_products_active ON products(active);

-- Insert sample data
INSERT INTO products (name, description, price, stock, category, sku) VALUES
('Laptop Pro', 'High-performance laptop for professionals', 1299.99, 50, 'Electronics', 'LAPTOP-PRO-001'),
('Wireless Mouse', 'Ergonomic wireless mouse', 29.99, 100, 'Accessories', 'MOUSE-WIRELESS-001'),
('Mechanical Keyboard', 'RGB mechanical keyboard', 149.99, 75, 'Accessories', 'KEYBOARD-MECH-001');
