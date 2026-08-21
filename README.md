# RecipeApp 🍲

A modern, clean Android application for discovering and viewing delicious recipes. Built with Jetpack Compose and Ktor, following clean architecture principles.

## ✨ Features

- **Recipe Discovery**: Browse a variety of recipes in a beautiful grid layout.
- **Category Filtering**: Quickly filter recipes by cuisine (e.g., Italian, Mexican, Chinese).
- **Detailed View**: View comprehensive details for each recipe, including:
    - High-quality imagery.
    - Nutritional information (Calories).
    - Ratings and Review counts.
    - Preparation and Cooking times.
    - Interactive Ingredients list.
    - Step-by-step cooking instructions.
- **Modern UI**: Clean White and Orange theme designed with Material 3.
- **Robust Error Handling**: Integrated loading states and "Retry" functionality for network failures.

## 🛠 Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Networking**: [Ktor](https://ktor.io/)
- **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/)
- **Navigation**: [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **State Management**: ViewModel & LiveData/State
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)

## 🏗 Architecture

The project follows the **Clean Architecture** pattern:
- **Data**: Ktor API service implementation and DTOs.
- **Domain**: Repository interfaces and business logic.
- **Presentation**: UI components (Composables) and ViewModels.

## 🚀 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/YOUR_USERNAME/RecipeApp.git
   ```
2. **Open in Android Studio**:
   Open the root folder and wait for Gradle to sync.
3. **Run the app**:
   Connect an Android device or emulator and click the "Run" button.

## 📸 Preview

<p align="center">
  <img src="WhatsApp%20Image%202026-08-21%20at%2018.25.04.jpeg" alt="Splash Screen" width="220" />
  <img src="WhatsApp%20Image%202026-08-21%20at%2018.25.05%20(1).jpeg" alt="Home Screen" width="220" />
  <img src="WhatsApp%20Image%202026-08-21%20at%2018.25.05.jpeg" alt="Recipe Details" width="220" />
</p>

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
