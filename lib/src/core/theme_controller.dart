import 'package:flutter/material.dart';

enum TabThemeSkin { liquidGlass, material, oneUi, nothing }

class ThemeController extends ChangeNotifier {
  ThemeMode mode = ThemeMode.dark;
  TabThemeSkin skin = TabThemeSkin.liquidGlass;

  void toggleDarkMode(bool isDark) {
    mode = isDark ? ThemeMode.dark : ThemeMode.light;
    notifyListeners();
  }

  void setSkin(TabThemeSkin value) {
    skin = value;
    notifyListeners();
  }

  ThemeData buildLightTheme() {
    switch (skin) {
      case TabThemeSkin.material:
        return ThemeData(
          useMaterial3: true,
          colorSchemeSeed: Colors.deepPurple,
          brightness: Brightness.light,
        );
      case TabThemeSkin.oneUi:
        return ThemeData(
          useMaterial3: true,
          brightness: Brightness.light,
          cardTheme: const CardTheme(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.all(Radius.circular(24)),
            ),
          ),
        );
      case TabThemeSkin.nothing:
        return ThemeData(
          useMaterial3: true,
          brightness: Brightness.light,
          textTheme: ThemeData.light().textTheme.apply(fontFamily: 'monospace'),
        );
      case TabThemeSkin.liquidGlass:
        return ThemeData(
          useMaterial3: true,
          brightness: Brightness.light,
          scaffoldBackgroundColor: Colors.white,
          appBarTheme: const AppBarTheme(backgroundColor: Colors.transparent),
        );
    }
  }

  ThemeData buildDarkTheme() {
    switch (skin) {
      case TabThemeSkin.material:
        return ThemeData(
          useMaterial3: true,
          colorSchemeSeed: Colors.teal,
          brightness: Brightness.dark,
        );
      case TabThemeSkin.oneUi:
        return ThemeData(
          useMaterial3: true,
          brightness: Brightness.dark,
          cardTheme: const CardTheme(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.all(Radius.circular(24)),
            ),
          ),
        );
      case TabThemeSkin.nothing:
        return ThemeData(
          useMaterial3: true,
          brightness: Brightness.dark,
          textTheme: ThemeData.dark().textTheme.apply(fontFamily: 'monospace'),
        );
      case TabThemeSkin.liquidGlass:
        return ThemeData(
          useMaterial3: true,
          brightness: Brightness.dark,
          scaffoldBackgroundColor: Colors.black,
          appBarTheme: const AppBarTheme(backgroundColor: Colors.transparent),
        );
    }
  }
}
