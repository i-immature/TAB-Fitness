import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../services/auth_service.dart';
import '../profile/onboarding_screen.dart';
import '../home/tab_shell.dart';

class AuthGate extends StatelessWidget {
  const AuthGate({super.key});

  @override
  Widget build(BuildContext context) {
    return Consumer<AuthService>(
      builder: (_, auth, __) {
        if (auth.isLoggedIn) {
          return const OnboardingScreen();
        }
        return const LoginScreen();
      },
    );
  }
}

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final _email = TextEditingController();
  final _password = TextEditingController();
  bool signUpMode = false;

  @override
  Widget build(BuildContext context) {
    final auth = context.read<AuthService>();
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(24),
        child: Center(
          child: ConstrainedBox(
            constraints: const BoxConstraints(maxWidth: 420),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Text('TAB Auth', style: Theme.of(context).textTheme.headlineMedium),
                const SizedBox(height: 16),
                TextField(controller: _email, decoration: const InputDecoration(labelText: 'Email')),
                TextField(
                  controller: _password,
                  decoration: const InputDecoration(labelText: 'Password'),
                  obscureText: true,
                ),
                const SizedBox(height: 12),
                FilledButton(
                  onPressed: () async {
                    if (signUpMode) {
                      await auth.signUp(email: _email.text, password: _password.text);
                    } else {
                      await auth.login(email: _email.text, password: _password.text);
                    }
                    if (!context.mounted) return;
                    Navigator.of(context).pushReplacement(MaterialPageRoute(builder: (_) => const TabShell()));
                  },
                  child: Text(signUpMode ? 'Sign Up' : 'Login'),
                ),
                TextButton(
                  onPressed: () => setState(() => signUpMode = !signUpMode),
                  child: Text(signUpMode ? 'Have an account? Login' : 'No account? Sign Up'),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
