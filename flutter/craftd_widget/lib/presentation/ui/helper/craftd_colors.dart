import 'package:flutter/material.dart';

class CraftDColor{
  static Color hexToColor(String? hexColor) {
    try{
      hexColor = hexColor!.replaceAll('#', '');
      if (hexColor.length == 6) {
        hexColor = 'FF$hexColor';
      }
      return Color(int.parse(hexColor, radix: 16));
    } catch (e) {
      return Colors.black;
    }
  }
}