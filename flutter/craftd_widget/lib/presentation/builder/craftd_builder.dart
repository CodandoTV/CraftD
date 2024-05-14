library craftd_widget;

import 'package:flutter/material.dart';

import '../craftd_view_listener.dart';

abstract class CraftDBuilder<T> {
  final String key;

  const CraftDBuilder({required this.key});

  T fromJson(dynamic properties);

  Widget craft(T model, CraftDViewListener listener);
}
