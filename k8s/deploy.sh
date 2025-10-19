#!/bin/bash

# Kubernetes Deployment Script for Vercel Microservices

echo "🚀 Starting Vercel Microservices Deployment..."

# Create namespaces
echo "📁 Creating namespaces..."
kubectl apply -f k8s/namespaces/

# Deploy PostgreSQL
echo "🗄️ Deploying PostgreSQL..."
kubectl apply -f k8s/configmaps/
kubectl apply -f k8s/deployments/postgres.yaml

# Wait for PostgreSQL to be ready
echo "⏳ Waiting for PostgreSQL to be ready..."
kubectl wait --for=condition=ready pod -l app=postgres -n vercel-database --timeout=300s

# Deploy Eureka Server
echo "🔍 Deploying Eureka Server..."
kubectl apply -f k8s/deployments/eureka-server.yaml

# Wait for Eureka Server to be ready
echo "⏳ Waiting for Eureka Server to be ready..."
kubectl wait --for=condition=ready pod -l app=eureka-server -n vercel-microservices --timeout=300s

# Deploy Microservices
echo "🔧 Deploying Microservices..."
kubectl apply -f k8s/deployments/user-service.yaml
kubectl apply -f k8s/deployments/product-service.yaml
kubectl apply -f k8s/deployments/api-gateway.yaml

# Deploy Ingress
echo "🌐 Deploying Ingress..."
kubectl apply -f k8s/ingress.yaml

echo "✅ Deployment completed!"
echo ""
echo "📊 Check deployment status:"
echo "kubectl get pods -n vercel-microservices"
echo "kubectl get pods -n vercel-database"
echo ""
echo "🌐 Access the application:"
echo "kubectl get ingress -n vercel-microservices"
echo ""
echo "📝 Add to /etc/hosts:"
echo "127.0.0.1 vercel.local"
