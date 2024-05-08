import '../action/action_properties.dart';

class TextProperties {
  const TextProperties({
    required this.text,
    this.textColorHex,
    this.textSize,
    this.textAllCaps,
    this.backgroundHex,
    this.actionProperties
  });

  final String text;
  final String? textColorHex;
  final String? textSize;
  final bool? textAllCaps;
  final String? backgroundHex;
  final ActionProperties? actionProperties;
}
