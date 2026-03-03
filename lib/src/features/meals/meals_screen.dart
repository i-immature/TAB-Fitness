import 'package:flutter/material.dart';

class MealsScreen extends StatelessWidget {
  const MealsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final meals = [
      'Chicken + Rice • Protein rich',
      'Greek Yogurt Bowl • High protein',
      'Oats + Peanut Butter • Balanced carbs/fat',
      'Paneer Salad • Lean meal option',
    ];
    return ListView(
      padding: const EdgeInsets.all(12),
      children: [
        const Card(
          child: ListTile(
            title: Text('Today macros'),
            subtitle: Text('Calories: 2200 • Protein: 145g • Carbs: 210g • Fat: 70g'),
          ),
        ),
        ...meals.map((m) => Card(child: ListTile(leading: const Icon(Icons.restaurant_menu), title: Text(m)))),
      ],
    );
  }
}
