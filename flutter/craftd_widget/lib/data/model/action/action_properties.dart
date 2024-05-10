import 'analytics_properties.dart';

class ActionProperties {
  final String? deeplink;
  final AnalyticsProperties? analyticsProperties;

  const ActionProperties({this.deeplink, this.analyticsProperties});

  factory ActionProperties.fromJson(dynamic properties) {
    return ActionProperties(
        deeplink: properties != null ? properties["deeplink"] : null,
        analyticsProperties: properties != null ? AnalyticsProperties
            .fromJson(
            properties[AnalyticsProperties.key]) : null);
  }

  static const String key = "actionProperties";
}