import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../core/theme_controller.dart';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  String ringtone = 'Classic Bell';

  @override
  Widget build(BuildContext context) {
    final theme = context.watch<ThemeController>();
    return Scaffold(
      appBar: AppBar(title: const Text('Settings')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          const Text('App theme selector'),
          DropdownButtonFormField<TabThemeSkin>(
            value: theme.skin,
            items: const [
              DropdownMenuItem(value: TabThemeSkin.liquidGlass, child: Text('Liquid Glass (Default)')),
              DropdownMenuItem(value: TabThemeSkin.material, child: Text('Material UI')),
              DropdownMenuItem(value: TabThemeSkin.oneUi, child: Text('One UI')),
              DropdownMenuItem(value: TabThemeSkin.nothing, child: Text('Nothing UI')),
            ],
            onChanged: (v) {
              if (v != null) theme.setSkin(v);
            },
          ),
          const SizedBox(height: 16),
          const Text('Ringtone / Reminder Control'),
          DropdownButtonFormField<String>(
            value: ringtone,
            items: const [
              DropdownMenuItem(value: 'Classic Bell', child: Text('Classic Bell')),
              DropdownMenuItem(value: 'Soft Chime', child: Text('Soft Chime')),
              DropdownMenuItem(value: 'Digital Alarm', child: Text('Digital Alarm')),
            ],
            onChanged: (v) => setState(() => ringtone = v ?? ringtone),
          ),
          const SizedBox(height: 32),
          const Center(child: Text('Version 1.0.0+1')),
        ],
      ),
    );
  }
}
