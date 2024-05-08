import 'package:flutter/src/widgets/framework.dart';
import '../../../data/model/action/action_properties.dart';
import '../../../data/model/button/button_properties.dart';
import '../../builder/craftd_builder.dart';
import '../../craftd_view_listener.dart';
import 'craftd_button.dart';

class CraftDButtonBuilder extends CraftDBuilder<ButtonProperties> {
  CraftDButtonBuilder() : super(key: key);

  @override
  Widget craft(ButtonProperties model, CraftDViewListener listener) {
    return CraftDButton(
      buttonProperties: model,
      callback: () {
        if (model.actionProperties != null) {
          listener(model.actionProperties!);
        }
      },
    );
  }

  @override
  ButtonProperties fromJson(properties) {
    return ButtonProperties(
        text: properties["text"],
        textColorHex: properties["textColorHex"],
        textSize: properties["textSize"],
        textAllCaps: properties["textAllCaps"],
        fillMaxSize: properties["fillMaxSize"],
        backgroundHex: properties["backgroundHex"],
        actionProperties: ActionProperties.fromJson(
            properties[ActionProperties.key]));
  }

  static String key = "CraftDButton";
}