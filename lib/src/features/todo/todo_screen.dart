import 'package:flutter/material.dart';

class TodoScreen extends StatefulWidget {
  const TodoScreen({super.key});

  @override
  State<TodoScreen> createState() => _TodoScreenState();
}

class _TodoScreenState extends State<TodoScreen> {
  final tasks = <Map<String, dynamic>>[
    {'title': 'Morning yoga', 'category': 'Health', 'done': true},
    {'title': 'Meal prep', 'category': 'Nutrition', 'done': false},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.all(12),
      itemCount: tasks.length,
      itemBuilder: (_, i) {
        final task = tasks[i];
        return Card(
          child: CheckboxListTile(
            value: task['done'] as bool,
            title: Text(task['title'] as String),
            subtitle: Text('Category: ${task['category']} • Reminder: daily'),
            onChanged: (v) => setState(() => task['done'] = v ?? false),
          ),
        );
      },
    );
  }
}
