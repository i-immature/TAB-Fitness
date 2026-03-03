# TAB – The Absolute Body (Android/Kotlin)

This repository now contains an **Android Studio compatible Kotlin app** scaffold for **TAB** using:
- **Kotlin + Jetpack Compose** for UI
- **Room** for local database
- **Navigation Compose** for tab navigation
- **Retrofit + Kotlinx Serialization** placeholder for JWT auth API

## Implemented app structure

- Splash/onboarding login-style entry screen with the TAB branding text
- Bottom tabs: Home, To-Do, Workouts, Meals
- Top bar with profile icon (left), TAB branding center, hamburger menu (right)
- Drawer menu entries:
  - Dark mode toggle
  - Add watch/sync button
  - Settings (theme/ringtone)
  - Logout
  - Linktree URL: https://linktr.ee/immature.ig
- Home dashboard summary with score out of 10 and floating 🤖 button
- To-Do list with add and checkbox completion
- Workout list with quick add (+) and 🤖 button
- Meals list with quick add (+) and 🤖 button

## Data model (Room)

Room entities include:
- Profile (picture URI, username, name, email, height, weight, goal)
- To-Do
- Workout
- Meal
- Watch sync

## Open in Android Studio

1. Open this folder in Android Studio.
2. Let Gradle sync.
3. Build and run on emulator/device.

> Note: This is a strong foundation scaffold for your full product vision. Advanced modules (real JWT backend wiring, alarm manager integration, full chatbot with voice, rich theming engines for Liquid Glass/One UI/Nothing UI, and wearable integration APIs) are prepared conceptually and should be implemented in iterative phases.
