import 'package:flutter/material.dart';

import '../home/tab_shell.dart';

class OnboardingScreen extends StatefulWidget {
  const OnboardingScreen({super.key});

  @override
  State<OnboardingScreen> createState() => _OnboardingScreenState();
}

class _OnboardingScreenState extends State<OnboardingScreen> {
  final name = TextEditingController();
  final username = TextEditingController();
  final height = TextEditingController(text: '170');
  final weight = TextEditingController(text: '70');
  String goal = 'Build physique';

  @override
  Widget build(BuildContext context) {
    final bmi = (double.tryParse(weight.text) ?? 70) /
        (((double.tryParse(height.text) ?? 170) / 100) * ((double.tryParse(height.text) ?? 170) / 100));
    return Scaffold(
      appBar: AppBar(title: const Text('First-time setup')),
      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          const Text('Add personal details'),
          TextField(controller: name, decoration: const InputDecoration(labelText: 'Name')),
          TextField(controller: username, decoration: const InputDecoration(labelText: 'Username')),
          TextField(controller: height, decoration: const InputDecoration(labelText: 'Height (cm)')),
          TextField(controller: weight, decoration: const InputDecoration(labelText: 'Weight (kg)')),
          const SizedBox(height: 8),
          Text('BMI: ${bmi.toStringAsFixed(1)}'),
          DropdownButtonFormField<String>(
            value: goal,
            items: const [
              DropdownMenuItem(value: 'Gain weight', child: Text('Gain weight')),
              DropdownMenuItem(value: 'Lose weight', child: Text('Lose weight')),
              DropdownMenuItem(value: 'Build physique', child: Text('Build physique')),
            ],
            onChanged: (v) => setState(() => goal = v ?? goal),
          ),
          const SizedBox(height: 16),
          FilledButton(
            onPressed: () {
              Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (_) => const TabShell()));
            },
            child: const Text('Continue to TAB'),
          )
        ],
      ),
    );
  }
}
