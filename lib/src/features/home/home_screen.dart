import 'package:fl_chart/fl_chart.dart';
import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        Text('Dashboard', style: Theme.of(context).textTheme.headlineSmall),
        const SizedBox(height: 8),
        Card(
          child: SizedBox(
            height: 220,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: BarChart(
                BarChartData(
                  barGroups: [
                    _group(0, 6),
                    _group(1, 7),
                    _group(2, 8),
                    _group(3, 5),
                  ],
                  titlesData: const FlTitlesData(show: false),
                  borderData: FlBorderData(show: false),
                  gridData: const FlGridData(show: false),
                ),
              ),
            ),
          ),
        ),
        const SizedBox(height: 12),
        Wrap(
          spacing: 10,
          runSpacing: 10,
          children: const [
            _ScoreCard(title: 'Workout kcal', value: '420'),
            _ScoreCard(title: 'Protein/Fat/Carb', value: '130/55/180g'),
            _ScoreCard(title: 'To-Do Complete', value: '8/10'),
            _ScoreCard(title: 'Sleep', value: '7h 42m'),
            _ScoreCard(title: 'Day Score', value: '8.3/10'),
          ],
        )
      ],
    );
  }

  BarChartGroupData _group(int x, double y) {
    return BarChartGroupData(x: x, barRods: [BarChartRodData(toY: y)]);
  }
}

class _ScoreCard extends StatelessWidget {
  const _ScoreCard({required this.title, required this.value});

  final String title;
  final String value;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 170,
      child: Card(
        child: ListTile(
          title: Text(value, style: Theme.of(context).textTheme.titleLarge),
          subtitle: Text(title),
        ),
      ),
    );
  }
}
