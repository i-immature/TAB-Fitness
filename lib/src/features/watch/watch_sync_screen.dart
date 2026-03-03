import 'package:flutter/material.dart';

class WatchSyncScreen extends StatelessWidget {
  const WatchSyncScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Watch Sync')),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            const Icon(Icons.watch, size: 64),
            const SizedBox(height: 12),
            const Text('Connect your watch or watch app to sync workouts and sleep.'),
            const SizedBox(height: 12),
            FilledButton(onPressed: () {}, child: const Text('Sync now')),
          ],
        ),
      ),
    );
  }
}
