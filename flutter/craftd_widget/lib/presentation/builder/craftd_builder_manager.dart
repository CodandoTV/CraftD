import '../ui/button/craftd_button_builder.dart';
import '../ui/empty/craftd_empty_builder.dart';
import '../ui/text/craftd_text_builder.dart';
import 'craftd_builder.dart';

class CraftDBuilderManager {
  static final Map<String, CraftDBuilder> _builders = {
    CraftDButtonBuilder.key: CraftDButtonBuilder(),
    CraftDTextBuilder.key: CraftDTextBuilder(),
  };

  static CraftDBuilder getBuilder(String key,
      {Map<String, CraftDBuilder>? customMap}) {
    if (customMap != null) {
      _builders.addAll(customMap);
    }
    return _builders[key] != null ? _builders[key]! : CraftDEmptyBuilder();
  }
}
