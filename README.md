# TAB Fitness (Android)

TAB is an Android Studio compatible Kotlin fitness application using **Jetpack Compose** for UI and **Room** for local database storage.

## Brand
- App name: **TAB The Absolute Body**
- Splash text: **TAB The Absolute Body**
- Package: `com.tabfitness.app`

## Included Features
- Splash experience with black background.
- Email/password auth gate with JWT-style local token session.
- First-time onboarding for profile and fitness goal.
- Bottom tabs: Home, To-Do, Workouts, Meals.
- Top-left profile button + top-right hamburger menu.
- Menu items: dark mode toggle, watch sync, settings placeholder, logout, Linktree text.
- Home dashboard summary for workouts, meals, tasks, sleep, and day score.
- Workout, meals, and to-do data entry with Room persistence.
- Trainer chatbot dialog (`🤖`) entry point.
- Theme engine with 4 skins:
  - Liquid Glass (default)
  - Material
  - One UI inspired
  - Nothing inspired

## Build
1. Open in Android Studio (Ladybug+ recommended).
2. Let Gradle sync.
3. Run app on API 26+ emulator/device.

## Notes
- Cloud JWT backend, OpenAI live chat, voice sessions, ringtone picker, and deep watch integrations are scaffolded conceptually in UI and architecture but require API keys/services to be production-ready.
