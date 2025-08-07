import 'package:craftd_widget/data/model/text/text_properties.dart';
import 'package:craftd_widget/presentation/ui/text/craftd_text.dart';
import 'package:flutter_test/flutter_test.dart';

import '../util/widget_util.dart';

void main() {
  testWidgets(
    'CraftD - Text with allcaps false',
        (tester) async {
      const textProperties = TextProperties(
        text: 'Tap',
        textColorHex: '#800080',
        backgroundHex: '#000000',
        textSize: '32.0',
        textAllCaps: false,
      );
      final widget = await WidgetsUtil.buildMaterialAppWidgetTest(
        child: CraftDText(
          textProperties: textProperties,
          callback: () => {},
        ),
        tester: tester,
      );

      await tester.pumpWidget(widget);

      await expectLater(
        find.byType(CraftDText),
        matchesGoldenFile('goldens/craftd_text_allcaps_false_snapshot.png'),
      );
    },
  );

  testWidgets(
    'CraftD - Text with allcaps true',
        (tester) async {
      const textProperties = TextProperties(
        text: 'Tap',
        textColorHex: '#800080',
        backgroundHex: '#000000',
        textSize: '32.0',
        textAllCaps: true,
      );
      final widget = await WidgetsUtil.buildMaterialAppWidgetTest(
        child: CraftDText(
          textProperties: textProperties,
          callback: () => {},
        ),
        tester: tester,
      );

      await tester.pumpWidget(widget);

      await expectLater(
        find.byType(CraftDText),
        matchesGoldenFile('goldens/craftd_text_allcaps_true_snapshot.png'),
      );
    },
  );
}
