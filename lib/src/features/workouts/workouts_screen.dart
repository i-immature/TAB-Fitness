import 'package:flutter/material.dart';

class WorkoutsScreen extends StatelessWidget {
  const WorkoutsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final workouts = [
      'Bench Press • Sets/Reps/Weight',
      'Squat • Sets/Reps/Weight',
      'Running • Distance/Time',
      'Cycling • Distance/Time',
      'Plank • Timer',
    ];

    return ListView(
      padding: const EdgeInsets.all(12),
      children: workouts
          .map((w) => Card(
                child: ListTile(
                  leading: const Icon(Icons.sports_gymnastics),
                  title: Text(w),
                  subtitle: const Text('Supports reminders and alarms.'),
                ),
              ))
          .toList(),
    );
  }
}
