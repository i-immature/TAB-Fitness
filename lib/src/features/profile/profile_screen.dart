import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  XFile? image;

  @override
  Widget build(BuildContext context) {
    const bmi = 23.4;
    return Scaffold(
      appBar: AppBar(title: const Text('Profile')),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          Center(
            child: GestureDetector(
              onTap: () async {
                final picked = await ImagePicker().pickImage(source: ImageSource.gallery);
                if (picked != null) setState(() => image = picked);
              },
              child: CircleAvatar(radius: 44, child: Text(image == null ? 'Add' : 'Set')),
            ),
          ),
          const SizedBox(height: 16),
          const ListTile(title: Text('Username'), subtitle: Text('@tabuser')),
          const ListTile(title: Text('Name'), subtitle: Text('TAB Athlete')),
          const ListTile(title: Text('Email'), subtitle: Text('athlete@tab.app')),
          const ListTile(title: Text('Height'), subtitle: Text('170 cm')),
          const ListTile(title: Text('Weight'), subtitle: Text('68 kg')),
          const Divider(),
          const Text('BMI Meter'),
          LinearProgressIndicator(value: bmi / 40),
          const SizedBox(height: 8),
          const Text('BMI Scale: Normal'),
        ],
      ),
    );
  }
}
