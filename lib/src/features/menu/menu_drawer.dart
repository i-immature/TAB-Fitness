import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../core/theme_controller.dart';
import '../../services/auth_service.dart';
import '../settings/settings_screen.dart';
import '../watch/watch_sync_screen.dart';

class MenuDrawer extends StatelessWidget {
  const MenuDrawer({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = context.watch<ThemeController>();
    return Drawer(
      child: SafeArea(
        child: Column(
          children: [
            SwitchListTile(
              title: const Text('Dark mode'),
              value: theme.mode == ThemeMode.dark,
              onChanged: theme.toggleDarkMode,
            ),
            ListTile(
              leading: const Icon(Icons.watch),
              title: const Text('Add Watch & Sync'),
              onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (_) => const WatchSyncScreen())),
            ),
            ListTile(
              leading: const Icon(Icons.settings),
              title: const Text('Settings'),
              onTap: () => Navigator.of(context).push(MaterialPageRoute(builder: (_) => const SettingsScreen())),
            ),
            const Spacer(),
            ListTile(
              leading: const Icon(Icons.link),
              title: const Text('Linktree'),
              subtitle: const Text('https://linktr.ee/immature.ig'),
            ),
            ListTile(
              leading: const Icon(Icons.logout),
              title: const Text('Logout'),
              onTap: context.read<AuthService>().logout,
            ),
          ],
        ),
      ),
    );
  }
}
