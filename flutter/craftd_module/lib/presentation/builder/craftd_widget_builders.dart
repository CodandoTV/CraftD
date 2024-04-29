import '../ui/button/craftd_button_builder.dart';
import '../ui/empty/craftd_empty_builder.dart';
import '../ui/text/craftd_text_builder.dart';
import 'craftd_widget_builder.dart';

class CraftDWidgetBuilders {
  static final Map<String, CraftDWidgetBuilder> _builders = {
    CraftDButtonBuilder.key: CraftDButtonBuilder(),
    CraftDTextBuilder.key: CraftDTextBuilder(),
  };

  static CraftDWidgetBuilder getBuilder(String key,
      {Map<String, CraftDWidgetBuilder>? customMap}) {
    if (customMap != null) {
      _builders.addAll(customMap);
    }
    return _builders[key] != null ? _builders[key]! : CraftDEmptyBuilder();
  }
}
