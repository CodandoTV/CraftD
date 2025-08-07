import 'package:craftd_widget/data/model/text/text_properties.dart';
import 'package:craftd_widget/presentation/ui/text/craftd_text.dart';
import 'package:flutter_test/flutter_test.dart';

import '../util/widget_util.dart';

const defaultBackgroundHexColor = '#FFFFFF';
const defaultTextHexColor = '#000000';
const defaultTextSize = '32.0';
const defaultTextAllCaps = false;
const defaultText = 'CodandoTV';

void main() {
  testWidgets(
    'CraftD - Text with allcaps false',
        (tester) async {
      const textProperties = TextProperties(
        text: defaultText,
        textColorHex: defaultTextHexColor,
        backgroundHex: defaultBackgroundHexColor,
        textSize: defaultTextSize,
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
        text: defaultText,
        textColorHex: defaultTextHexColor,
        backgroundHex: defaultBackgroundHexColor,
        textSize: defaultTextSize,
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

  testWidgets(
    'CraftD - Text with orange text color',
        (tester) async {
      const textProperties = TextProperties(
        text: defaultText,
        backgroundHex: defaultBackgroundHexColor,
        textSize: defaultTextSize,
        textAllCaps: defaultTextAllCaps,
        textColorHex: '#FFA500',
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
        matchesGoldenFile('goldens/craftd_text_with_orange_text_color_snapshot.png'),
      );
    },
  );

  testWidgets(
    'CraftD - Text with orange background color',
        (tester) async {
      const textProperties = TextProperties(
        text: defaultText,
        textColorHex: defaultTextHexColor,
        textSize: defaultTextSize,
        backgroundHex: '#FFA500',
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
        matchesGoldenFile('goldens/craftd_text_with_orange_background_color_snapshot.png'),
      );
    },
  );

  testWidgets(
    'CraftD - Text with a big font size',
        (tester) async {
      const textProperties = TextProperties(
        textSize: '80.0',
        text: defaultText,
        textColorHex: defaultTextHexColor,
        backgroundHex: defaultBackgroundHexColor,
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
        matchesGoldenFile('goldens/craftd_text_with_big_font_size_snapshot.png'),
      );
    },
  );
}
