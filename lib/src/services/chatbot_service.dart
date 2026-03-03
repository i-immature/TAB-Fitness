class ChatbotService {
  Future<String> askTrainer(String prompt) async {
    // Replace with your OpenAI backend endpoint.
    await Future<void>.delayed(const Duration(milliseconds: 300));
    return 'Trainer AI: Great effort. Based on your goal, stay consistent, hydrate, and complete your planned sets today.';
  }
}
