# 🛡️ NEBOSH IGC Master - Safety Study App

A comprehensive, multi-language educational Android application designed for Occupational Health and Safety professionals preparing for the NEBOSH IGC exam. Built with **Modern Android Development (MAD)** standards using **Jetpack Compose** and **Clean Architecture**.

## 🚀 Features

* **📚 Interactive Study Modules:** Complete IG1 & IG2 syllabus content with rich text support.
* **🌍 Multi-Language Support:** Dynamic localization for **English, Polish, German, and Turkish**.
* **🧠 Smart Quiz System:** Topic-based quizzes with instant feedback and score tracking.
* **⚡ 5x5 Risk Matrix Calculator:** Custom tool to calculate risk scores based on Likelihood x Severity, providing instant visual feedback (Green/Amber/Red).
* **📖 Searchable Glossary:** A-Z technical terms dictionary with instant search filtering.
* **🌑 Dark/Light Theme:** Full support for system-wide dark mode for comfortable reading.
* **💾 Offline First:** All data is persisted locally using Room Database.

## 🛠️ Tech Stack & Architecture

This project demonstrates modern Android development practices:

* **Language:** 100% [Kotlin](https://kotlinlang.org/)
* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
* **Architecture:** MVVM (Model-View-ViewModel)
* **Local Data:** [Room Database](https://developer.android.com/training/data-storage/room) (SQLite abstraction)
* **Concurrency:** Kotlin Coroutines & Flow
* **Navigation:** Jetpack Navigation Compose
* **Data Parsing:** GSON for JSON asset parsing
* **Compatibility:** Minimum SDK 24, Target SDK 34

## 📱 Screenshots
https://imgur.com/a/liluYvh

## 💡 Key Highlights

### 1. Clean Architecture & MVVM
The app follows the **Separation of Concerns** principle. The UI layer (Composables) observes the `ViewModel`, which communicates with the `Repository` to fetch data from the `Room Database`. This ensures the app is testable and maintainable.

### 2. Complex Data Handling
The app pre-populates the database from complex JSON structures upon first launch, ensuring the user has immediate offline access to hundreds of questions and glossary terms.

### 3. Dynamic Theming
Utilizes `isSystemInDarkTheme()` and custom Color Schemes to provide a seamless visual experience that adapts to the user's device settings.

## 📥 Installation

1.  Clone the repository:
    ```bash
    git clone [https://github.com/username/Nebosh-IGC-Learning-App.git](https://github.com/username/Nebosh-IGC-Learning-App.git)
    ```
2.  Open in **Android Studio Koala/Jellyfish** (or newer).
3.  Sync Gradle and Run on Emulator or Physical Device.

## 👨‍💻 Author

Erkin Kenar - *Android Developer based in Poland*

I am passionate about building efficient, user-centric mobile applications using the latest technologies.

https://www.linkedin.com/in/erkinkenar/

https://github.com/erkinkenar

---
*This project is for educational purposes and demonstrates my skills in Android Development.*
