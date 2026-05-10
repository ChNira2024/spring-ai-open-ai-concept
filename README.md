# 🤖 Spring AI Demo Application

## 📌 Overview

This project is an AI-powered backend application built using Spring Boot and Spring AI. It integrates OpenAI APIs to provide intelligent features such as chat responses, image generation, and recipe creation based on user inputs.

---

## 🚀 Features

* 💬 AI Chat Response Generation
* 🖼️ AI Image Generation (custom size, quality, count)
* 🍲 AI Recipe Generator using prompt templates
* 📜 Swagger (OpenAPI) API Documentation
* 📊 Spring Boot Actuator for monitoring
* 🧾 Global Exception Handling
* 🪵 Logging using SLF4J
* 🔐 Secure API key management using environment variables
* 🌐 CORS configuration for frontend integration

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring AI
* OpenAI API
* Maven
* SpringDoc OpenAPI (Swagger)
* Spring Boot Actuator

---

## 📂 API Endpoints

### 🔹 Chat API

GET /api/ai/chat?prompt=your_text

### 🔹 Recipe API

GET /api/ai/recipe?ingredients=...&cuisine=...&diet=...

### 🔹 Image API

POST /api/ai/image

---

## ⚙️ Configuration

Set your OpenAI API key as an environment variable:

MY_APP_KEY=your_api_key

---

## 📊 Monitoring

* Health Check: http://localhost:8080/actuator/health
* Metrics: http://localhost:8080/actuator/metrics

---

## 📘 API Documentation

Swagger UI:
http://localhost:8080/swagger-ui.html

---
