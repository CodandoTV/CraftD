import 'package:craftd_module/craftd_module.export.dart';
import 'package:flutter/material.dart';

import '../builder/craftd_widget_builders.dart';

class CraftDWidgetController extends StatelessWidget {
  final List<SimpleProperties> simplePropertiesList;
  final Function(ActionProperties) onAction;

  const CraftDWidgetController(
      {super.key, required this.simplePropertiesList, required this.onAction});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: simplePropertiesList.length,
        itemBuilder: (context, index) {
          final SimpleProperties simpleProperties = simplePropertiesList[index];
          final craftdBuilder =
              CraftDWidgetBuilders.getBuilder(simpleProperties.key);
          return craftdBuilder
              .craft(craftdBuilder.fromJson(simpleProperties.value),
                  (actionProperties) {
            onAction(actionProperties);
          });
        });
  }
}
