# 📦 APK to QA

A simple Android Studio plugin that **automatically shares your APK via email** with QA/testers right after a successful build. Save time, skip the manual steps, and streamline your testing pipeline.

---

## ✨ Features

- ✅ Auto-detects APK after build
- 📧 Sends APK directly via email
- ⚙️ Configurable recipient(s), subject, and message
- 🔐 No need to upload to external services

---

## 🚀 How It Works

1. After building your Android app in Android Studio
2. The plugin grabs the generated APK
3. Sends it to your configured email (or QA team) instantly

---

## 🛠 Setup

1. Clone or download this repository.
2. Open it in **IntelliJ IDEA** or **Android Studio**.
3. Build the plugin using `./gradlew buildPlugin`.
4. Install the plugin in Android Studio:
   - Go to `Preferences > Plugins > ⚙️ > Install Plugin from Disk`
   - Select the generated `.jar` file from `build/distributions/`
5. Configure your email settings in the plugin panel.

---

## 📎 Configuration

You'll be able to set:
- ✅ Recipient email addresses
- ✏️ Email subject & message
- 📁 APK file location (auto-detected, but override if needed)

---

## 📷 Screenshots

*(Optional: Add screenshots of the plugin UI and email output here)*

---

## 🧠 Why This Exists

Sharing builds manually is repetitive. This plugin automates the process so you can:
- Deliver faster to QA
- Stay in flow without switching tools
- Make mobile CI simple even without a full CI/CD setup

---

## 🧑‍💻 Author

Made with 💻 by [Sachin Singh](https://github.com/sachin3618)  
📬 Reach out: ss3618255@gmail.com

---

## ⭐️ Show Support

If you find this plugin useful:
- 🌟 Star this repo
- 🍴 Fork and contribute
- 🗣️ Share it with your team
