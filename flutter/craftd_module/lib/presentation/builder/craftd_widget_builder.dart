library craftd_module;

import 'package:flutter/material.dart';

import '../craftd_view_listener.dart';

abstract class CraftDWidgetBuilder<T> {
  const CraftDWidgetBuilder({required String key});

  T fromJson(dynamic properties);

  Widget craft(T model, CraftDViewListener listener);
}
