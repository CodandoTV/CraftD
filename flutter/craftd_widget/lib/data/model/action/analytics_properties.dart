class AnalyticsProperties {
  final String? category;
  final String? action;
  final String? label;
  final String? track;

  const AnalyticsProperties(
      {this.category, this.label, this.action, this.track});

  factory AnalyticsProperties.fromJson(dynamic properties) {
    return AnalyticsProperties(
      category: properties != null ? properties['category'] : null,
      action: properties != null ? properties['action'] : null,
      label: properties != null ? properties['label'] : null,
      track: properties != null ? properties['track'] : null,
    );
  }

  static String key = 'analytics';
}
