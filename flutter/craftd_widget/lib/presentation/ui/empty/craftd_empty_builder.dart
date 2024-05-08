import 'package:flutter/material.dart';
import 'package:craftd_widget/data/model/empty/empty_properties.dart';
import '../../builder/craftd_builder.dart';
import '../../craftd_view_listener.dart';
import 'craftd_empty.dart';

class CraftDEmptyBuilder extends CraftDBuilder<EmptyProperties> {
  CraftDEmptyBuilder() : super(key: key);

  @override
  Widget craft(EmptyProperties model, CraftDViewListener listener) {
    return const CraftDEmpty();
  }

  @override
  EmptyProperties fromJson(properties) {
    return const EmptyProperties();
  }
  static String key = "CraftDEmpty";
}