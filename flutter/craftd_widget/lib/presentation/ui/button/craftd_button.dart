import 'package:flutter/material.dart';
import '../../../data/model/button/button_properties.dart';
import '../../../data/model/text/text_properties.dart';
import '../text/craftd_text.dart';

class CraftDButton extends StatelessWidget {
  final VoidCallback callback;
  final ButtonProperties buttonProperties;

  const CraftDButton(
      {super.key, required this.buttonProperties, required this.callback});

  @override
  Widget build(BuildContext context) {
    if (buttonProperties.fillMaxSize == true) {
      return SizedBox(
        child: _buildButton(),
        width: double.infinity,
      );
    } else {
      return _buildButton();
    }
  }

  _buildButton() {
    return ElevatedButton(
      onPressed: () {
        callback();
      },
      child: CraftDText(
        textProperties: TextProperties(
          text: buttonProperties.text,
          textColorHex: buttonProperties.textColorHex,
          textSize: buttonProperties.textSize,
          textAllCaps: buttonProperties.textAllCaps,
          backgroundHex: buttonProperties.backgroundHex,
        ),
      ),
    );
  }
}
