# TAB Fitness (Flutter)

TAB stands for **The Absolute Body**.

This implementation includes:
- Splash branding screen with black/white background and TAB title.
- First-time auth flow (email/password) with JWT-style custom auth service scaffold.
- First-time onboarding for profile, BMI, and goal selection.
- Main app shell with Home, To-Do, Workouts, Meals tabs.
- Profile button (top-left), hamburger menu (top-right), centered TAB branding.
- Drawer controls: dark mode toggle, watch sync page, settings page, logout, and Linktree reference.
- Theme skins: Liquid Glass (default), Material UI, One UI, Nothing-inspired.
- Home dashboard with progress chart and score cards.
- Workout, To-Do, and Meals starter flows with add affordances.
- Floating 🤖 trainer chat assistant in relevant tabs.
- Firebase data service scaffold (Firestore collections for profile/workouts/meals/todos).

## Firebase setup
1. Create a Firebase project.
2. Add Android/iOS/Web apps in Firebase Console.
3. Add generated config files to the Flutter project (`google-services.json`, `GoogleService-Info.plist`, etc.).
4. Replace default Firebase initialization with generated `firebase_options.dart` if using FlutterFire CLI.

## JWT custom auth
- `AuthService` currently calls a placeholder endpoint (`https://example.com/api/auth/login`).
- Replace endpoint with your backend and return `{ "token": "..." }`.
- Persist JWT in secure storage for production.

## AI chatbot integration
- `ChatbotService` is a local stub.
- Replace with backend service that calls OpenAI/ChatGPT APIs.
- Keep API keys on server side (not in app binary).
