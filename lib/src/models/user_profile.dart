class UserProfile {
  UserProfile({
    required this.username,
    required this.name,
    required this.email,
    required this.heightCm,
    required this.weightKg,
    required this.goal,
    this.photoUrl,
  });

  final String username;
  final String name;
  final String email;
  final double heightCm;
  final double weightKg;
  final String goal;
  final String? photoUrl;

  double get bmi => weightKg / ((heightCm / 100) * (heightCm / 100));

  String get bmiScale {
    if (bmi < 18.5) return 'Underweight';
    if (bmi < 25) return 'Normal';
    if (bmi < 30) return 'Overweight';
    return 'Obese';
  }

  Map<String, dynamic> toMap() => {
        'username': username,
        'name': name,
        'email': email,
        'heightCm': heightCm,
        'weightKg': weightKg,
        'goal': goal,
        'photoUrl': photoUrl,
      };

  factory UserProfile.fromMap(Map<String, dynamic> map) => UserProfile(
        username: map['username'] as String? ?? '',
        name: map['name'] as String? ?? '',
        email: map['email'] as String? ?? '',
        heightCm: (map['heightCm'] as num?)?.toDouble() ?? 170,
        weightKg: (map['weightKg'] as num?)?.toDouble() ?? 70,
        goal: map['goal'] as String? ?? 'Build physique',
        photoUrl: map['photoUrl'] as String?,
      );
}
