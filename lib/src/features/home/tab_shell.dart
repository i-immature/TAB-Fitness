import 'package:flutter/material.dart';

import '../chatbot/chatbot_sheet.dart';
import '../meals/meals_screen.dart';
import '../menu/menu_drawer.dart';
import '../profile/profile_screen.dart';
import '../todo/todo_screen.dart';
import '../workouts/workouts_screen.dart';
import 'home_screen.dart';

class TabShell extends StatefulWidget {
  const TabShell({super.key});

  @override
  State<TabShell> createState() => _TabShellState();
}

class _TabShellState extends State<TabShell> {
  int index = 0;

  @override
  Widget build(BuildContext context) {
    const pages = [HomeScreen(), TodoScreen(), WorkoutsScreen(), MealsScreen()];
    final labels = ['Home', 'To-Do', 'Workouts', 'Meals'];
    return Scaffold(
      drawer: const MenuDrawer(),
      appBar: AppBar(
        leading: IconButton(
          icon: const Icon(Icons.account_circle_outlined),
          onPressed: () {
            Navigator.of(context).push(MaterialPageRoute(builder: (_) => const ProfileScreen()));
          },
        ),
        title: const Text('TAB', style: TextStyle(fontWeight: FontWeight.bold, letterSpacing: 2)),
        centerTitle: true,
        actions: [Builder(builder: (context) {
          return IconButton(
            icon: const Icon(Icons.menu),
            onPressed: () => Scaffold.of(context).openDrawer(),
          );
        })],
      ),
      body: pages[index],
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      floatingActionButton: Row(
        mainAxisSize: MainAxisSize.min,
        children: [
          if (index != 0)
            FloatingActionButton.small(
              heroTag: 'add',
              onPressed: () => _showAddDialog(context, labels[index]),
              child: const Icon(Icons.add),
            ),
          const SizedBox(width: 12),
          FloatingActionButton(
            heroTag: 'bot',
            onPressed: () => showModalBottomSheet(
              context: context,
              isScrollControlled: true,
              builder: (_) => const ChatbotSheet(),
            ),
            child: const Text('🤖'),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: index,
        onTap: (i) => setState(() => index = i),
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.home), label: 'Home'),
          BottomNavigationBarItem(icon: Icon(Icons.check_box), label: 'To-Do'),
          BottomNavigationBarItem(icon: Icon(Icons.fitness_center), label: 'Workouts'),
          BottomNavigationBarItem(icon: Icon(Icons.restaurant), label: 'Meals'),
        ],
      ),
    );
  }

  void _showAddDialog(BuildContext context, String section) {
    showDialog<void>(
      context: context,
      builder: (_) => AlertDialog(
        title: Text('Add $section entry'),
        content: Text('In $section, you can add timers, reps, distance, nutrition, reminders, and categories.'),
        actions: [TextButton(onPressed: () => Navigator.pop(context), child: const Text('OK'))],
      ),
    );
  }
}
