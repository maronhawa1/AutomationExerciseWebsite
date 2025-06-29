# 🧪 AutomationExercise Website – Test Automation Project

This repository contains a **Test Automation Framework** for validating the core functionality of the [Automation Exercise](https://automationexercise.com) website.  
The framework is built using **Java**, **Selenium WebDriver**, **TestNG**, and **Allure Reports**.

---

## 📦 Tech Stack

- **Java 17+**
- **Selenium WebDriver**
- **TestNG**
- **Allure Reporting**
- **Maven**
- **Page Object Model (POM)** design

---

## ✅ Test Scenarios Implemented

| Test Class                  | Description                                      |
|----------------------------|--------------------------------------------------|
| `LoginTest`                | Validates login with correct credentials         |
| `RegisterTest`             | Verifies user registration with valid data       |
| `DeleteAccountTest`        | Tests account deletion flow                      |
| `PaymentTest`              | Tests full checkout + payment process            |
| `CheckoutWithoutLoginTest` | Ensures guests are redirected to login on checkout |

---

## 📁 Project Structure
AutomationExercise_Website/
├── src/test/java/
│ ├── base/ # BaseTest class for WebDriver setup
│ ├── pages/ # Page Object classes
│ └── tests/ # Test classes (TestNG)
├── testng.xml # Test Suite configuration
├── pom.xml # Maven dependencies
└── README.md

---

## ▶️ How to Run the Tests

### 🖥️ Option 1: From IntelliJ IDEA

1. Open the project in IntelliJ.
2. Right-click on `testng.xml` → `Run 'AllTests'`.
3. View results in the `Run` window.
4. Allure results will be generated in `target/allure-results`.

---

### 💻 Option 2: From Terminal (Maven CLI)

> Make sure you have Maven and Java set up in your system.

```bash
# Run all tests
mvn clean test
