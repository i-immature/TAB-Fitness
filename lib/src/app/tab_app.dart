import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../core/theme_controller.dart';
import '../features/auth/auth_gate.dart';

class TabFitnessApp extends StatelessWidget {
  const TabFitnessApp({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = context.watch<ThemeController>();
    return MaterialApp(
      title: 'TAB The Absolute Body',
      debugShowCheckedModeBanner: false,
      themeMode: theme.mode,
      theme: theme.buildLightTheme(),
      darkTheme: theme.buildDarkTheme(),
      home: const SplashScreen(),
    );
  }
}

class SplashScreen extends StatefulWidget {
  const SplashScreen({super.key});

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    super.initState();
    Future<void>.delayed(const Duration(seconds: 2), () {
      if (!mounted) return;
      Navigator.of(context).pushReplacement(
        MaterialPageRoute(builder: (_) => const AuthGate()),
      );
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).brightness == Brightness.dark ? Colors.black : Colors.white,
      body: Center(
        child: Text(
          'TAB\nThe Absolute Body',
          textAlign: TextAlign.center,
          style: Theme.of(context).textTheme.headlineLarge?.copyWith(fontWeight: FontWeight.w700),
        ),
      ),
    );
  }
}
