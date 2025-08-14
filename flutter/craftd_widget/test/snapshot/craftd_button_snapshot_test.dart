import 'package:craftd_widget/data/model/button/button_properties.dart';
import 'package:craftd_widget/presentation/ui/button/craftd_button.dart';
import 'package:flutter_test/flutter_test.dart';

import '../util/widget_util.dart';

const defaultBackgroundHexColor = '#FFFFFF';
const defaultTextHexColor = '#000000';
const defaultTextSize = '32.0';
const defaultFillMaxSize = false;
const defaultText = 'CodandoTV';

void main() {
  testWidgets(
    'CraftD - Button with a fillMaxSize false',
    (tester) async {
      const buttonProperties = ButtonProperties(
        text: defaultText,
        textColorHex: defaultTextHexColor,
        backgroundHex: defaultBackgroundHexColor,
        textSize: defaultTextSize,
        fillMaxSize: false,
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
        matchesGoldenFile(
            'goldens/craftd_button_fill_max_size_false_snapshot.png'),
      );
    },
  );

  testWidgets(
    'CraftD - Button with a fillMaxSize true',
    (tester) async {
      const buttonProperties = ButtonProperties(
        text: defaultText,
        textColorHex: defaultTextHexColor,
        backgroundHex: defaultBackgroundHexColor,
        textSize: defaultTextSize,
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
        matchesGoldenFile(
            'goldens/craftd_button_fill_max_size_true_snapshot.png'),
      );
    },
  );
}
