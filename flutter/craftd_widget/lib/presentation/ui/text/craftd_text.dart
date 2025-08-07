import 'package:flutter/material.dart';
import 'package:craftd_widget/data/model/text/text_properties.dart';

import '../helper/craftd_colors.dart';

class CraftDText extends StatelessWidget {
  final TextProperties textProperties;
  final VoidCallback? callback;

  const CraftDText({super.key, required this.textProperties, this.callback});

  @override
  Widget build(BuildContext context) {
    final String text = textProperties.textAllCaps == true
        ? textProperties.text.toUpperCase()
        : textProperties.text;
    return GestureDetector(
      onTap: callback,
      child: Text(
        text,
        style: TextStyle(
          fontSize: textProperties.textSize != null
              ? double.tryParse(textProperties.textSize!)
              : 16.0,
          color: CraftDColor.hexToColor(textProperties.textColorHex),
          backgroundColor: CraftDColor.hexToColor(textProperties.backgroundHex),
        ),
      ),
    );
  }
}
