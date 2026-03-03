import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../services/chatbot_service.dart';

class ChatbotSheet extends StatefulWidget {
  const ChatbotSheet({super.key});

  @override
  State<ChatbotSheet> createState() => _ChatbotSheetState();
}

class _ChatbotSheetState extends State<ChatbotSheet> {
  final controller = TextEditingController();
  final messages = <String>['Trainer AI: Welcome to TAB. Tell me your goal and days/week.'];

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: MediaQuery.of(context).size.height * 0.78,
      child: Column(
        children: [
          const SizedBox(height: 12),
          Text('Fitness Trainer AI', style: Theme.of(context).textTheme.titleLarge),
          Expanded(
            child: ListView(
              padding: const EdgeInsets.all(12),
              children: messages.map((m) => Card(child: Padding(padding: const EdgeInsets.all(12), child: Text(m)))).toList(),
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(12),
            child: Row(
              children: [
                Expanded(child: TextField(controller: controller, decoration: const InputDecoration(hintText: 'Ask trainer...'))),
                IconButton(
                  icon: const Icon(Icons.send),
                  onPressed: () async {
                    final prompt = controller.text.trim();
                    if (prompt.isEmpty) return;
                    setState(() => messages.add('You: $prompt'));
                    controller.clear();
                    final reply = await context.read<ChatbotService>().askTrainer(prompt);
                    setState(() => messages.add(reply));
                  },
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
