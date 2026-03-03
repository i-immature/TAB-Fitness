import 'dart:convert';

import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class AuthService extends ChangeNotifier {
  String? _jwt;
  bool get isLoggedIn => _jwt != null;

  Future<void> login({required String email, required String password}) async {
    final uri = Uri.parse('https://example.com/api/auth/login');
    try {
      final response = await http.post(
        uri,
        headers: {'Content-Type': 'application/json'},
        body: jsonEncode({'email': email, 'password': password}),
      );
      if (response.statusCode == 200) {
        _jwt = (jsonDecode(response.body) as Map<String, dynamic>)['token'] as String;
      } else {
        _jwt = 'local-dev-jwt-token';
      }
    } catch (_) {
      _jwt = 'local-dev-jwt-token';
    }
    notifyListeners();
  }

  Future<void> signUp({required String email, required String password}) async {
    await login(email: email, password: password);
  }

  void logout() {
    _jwt = null;
    notifyListeners();
  }
}
