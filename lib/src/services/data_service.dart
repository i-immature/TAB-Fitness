import 'package:cloud_firestore/cloud_firestore.dart';

import '../models/user_profile.dart';

class DataService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Future<void> saveProfile(String uid, UserProfile profile) async {
    await _firestore.collection('users').doc(uid).set(profile.toMap(), SetOptions(merge: true));
  }

  Stream<UserProfile> profileStream(String uid) {
    return _firestore.collection('users').doc(uid).snapshots().map(
          (snap) => UserProfile.fromMap(snap.data() ?? {}),
        );
  }

  Future<void> addWorkout(String uid, Map<String, dynamic> workout) async {
    await _firestore.collection('users').doc(uid).collection('workouts').add(workout);
  }

  Future<void> addMeal(String uid, Map<String, dynamic> meal) async {
    await _firestore.collection('users').doc(uid).collection('meals').add(meal);
  }

  Future<void> addTodo(String uid, Map<String, dynamic> task) async {
    await _firestore.collection('users').doc(uid).collection('todos').add(task);
  }
}
