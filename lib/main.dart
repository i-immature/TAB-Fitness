import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'src/app/tab_app.dart';
import 'src/core/theme_controller.dart';
import 'src/services/auth_service.dart';
import 'src/services/chatbot_service.dart';
import 'src/services/data_service.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  try {
    await Firebase.initializeApp();
  } catch (_) {
    // Firebase setup can be completed by adding platform-specific config files.
  }

  runApp(
    MultiProvider(
      providers: [
        ChangeNotifierProvider(create: (_) => ThemeController()),
        ChangeNotifierProvider(create: (_) => AuthService()),
        Provider(create: (_) => DataService()),
        Provider(create: (_) => ChatbotService()),
      ],
      child: const TabFitnessApp(),
    ),
  );
}
