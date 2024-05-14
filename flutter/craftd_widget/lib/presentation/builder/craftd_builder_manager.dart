import '../ui/button/craftd_button_builder.dart';
import '../ui/empty/craftd_empty_builder.dart';
import '../ui/text/craftd_text_builder.dart';
import 'craftd_builder.dart';

class CraftDBuilderManager {
  final Map<String, CraftDBuilder> _builders = {
    CraftDButtonBuilder.keyBuilder: CraftDButtonBuilder(),
    CraftDTextBuilder.keyBuilder: CraftDTextBuilder(),
  };

  final Map<String, CraftDBuilder> customBuilders = {};

  CraftDBuilderManager add(List<CraftDBuilder> arrayCraftDBuilder) {
    for (var it in arrayCraftDBuilder) {
      _builders[it.key] = it;
  }
    return this;
  }

  CraftDBuilder getBuilder(String key) {
    if (customBuilders.isNotEmpty) {
      _builders.addAll(customBuilders);
    }
    return _builders[key] != null ? _builders[key]! : CraftDEmptyBuilder();
  }
}
