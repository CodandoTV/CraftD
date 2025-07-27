import 'package:flutter/src/widgets/framework.dart';
import 'package:craftd_widget/data/model/text/text_properties.dart';

import '../../../data/model/action/action_properties.dart';
import '../../builder/craftd_builder.dart';
import '../../craftd_view_listener.dart';
import 'craftd_text.dart';

class CraftDTextBuilder extends CraftDBuilder<TextProperties> {
  CraftDTextBuilder() : super(key: keyBuilder);
  static String keyBuilder = 'CraftDTextView';

  @override
  Widget craft(TextProperties model, CraftDViewListener listener) {
    return CraftDText(
        textProperties: model,
        callback: () {
          if (model.actionProperties != null) {
            listener(model.actionProperties!);
          }
        });
  }

  @override
  TextProperties fromJson(properties) {
    return TextProperties(
        text: properties['text'],
        textColorHex: properties['textColorHex'],
        textSize: properties['textSize'],
        textAllCaps: properties['textAllCaps'],
        backgroundHex: properties['backgroundHex'],
        actionProperties: ActionProperties.fromJson(
            properties[ActionProperties.key]));
  }

}
