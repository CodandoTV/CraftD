import 'package:craftd_widget/craftd_widget.export.dart';
import 'package:craftd_widget/presentation/builder/craftd_builder_manager.dart';
import 'package:flutter/material.dart';

class CraftDynamic extends StatelessWidget {
  final List<SimpleProperties> simplePropertiesList;
  final CraftDBuilderManager craftDBuilderManager;
  final Function(ActionProperties) onAction;

  const CraftDynamic(
      {super.key,
      required this.simplePropertiesList,
      required this.craftDBuilderManager,
      required this.onAction});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: simplePropertiesList.length,
        itemBuilder: (context, index) {
          final simpleProperties = simplePropertiesList[index];
          final craftdBuilder =
              craftDBuilderManager.getBuilder(simpleProperties.key);

          return craftdBuilder
              .craft(craftdBuilder.fromJson(simpleProperties.value),
                  (actionProperties) {
            onAction(actionProperties);
          });
        });
  }
}
