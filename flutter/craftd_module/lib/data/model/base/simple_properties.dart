class SimpleProperties {
  final String key;
  final dynamic value;

  const SimpleProperties({required this.key, required this.value});

  factory SimpleProperties.fromJson(dynamic properties) {
    return SimpleProperties(
        key: properties["key"],
        value: properties["value"]);
  }
}
