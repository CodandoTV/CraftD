import 'package:craftd_widget/data/model/button/button_properties.dart';
import 'package:craftd_widget/presentation/ui/button/craftd_button.dart';
import 'package:flutter_test/flutter_test.dart';

import '../util/widget_util.dart';

void main() {
  testWidgets(
    'CraftD - Button with allcaps true',
    (tester) async {
      const buttonProperties = ButtonProperties(
        text: 'Tap',
        textColorHex: '#800080',
        backgroundHex: '#000000',
        textSize: '32.0',
        textAllCaps: true,
      );
      final widget = await WidgetsUtil.buildMaterialAppWidgetTest(
        child: CraftDButton(
          buttonProperties: buttonProperties,
          callback: () => {},
        ),
        tester: tester,
      );

      await tester.pumpWidget(widget);

      await expectLater(
        find.byType(CraftDButton),
        matchesGoldenFile('goldens/craftd_button_allcaps_snapshot.png'),
      );
    },
  );

  testWidgets(
    'CraftD - Button with a fillMaxSize true',
        (tester) async {
      const buttonProperties = ButtonProperties(
        text: 'Tap',
        textColorHex: '#000000',
        backgroundHex: '#800080',
        textSize: '32.0',
        fillMaxSize: true,
      );
      final widget = await WidgetsUtil.buildMaterialAppWidgetTest(
        child: CraftDButton(
          buttonProperties: buttonProperties,
          callback: () => {},
        ),
        tester: tester,
      );

      await tester.pumpWidget(widget);

      await expectLater(
        find.byType(CraftDButton),
        matchesGoldenFile('goldens/craftd_button_fill_max_size_snapshot.png'),
      );
    },
  );
}
