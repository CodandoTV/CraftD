import 'package:craftd_widget/presentation/ui/empty/craftd_empty.dart';
import 'package:flutter_test/flutter_test.dart';

import '../util/widget_util.dart';

void main() {
  testWidgets(
    'CraftD - Empty',
        (tester) async {
      final widget = await WidgetsUtil.buildMaterialAppWidgetTest(
        child: const CraftDEmpty(),
        tester: tester,
      );

      await tester.pumpWidget(widget);

      await expectLater(
        find.byType(CraftDEmpty),
        matchesGoldenFile('goldens/craftd_empty_snapshot.png'),
      );
    },
  );
}
