import 'package:craftd_module/craftd_module.export.dart';
import 'package:craftd_module/presentation/builder/craftd_builder_manager.dart';
import 'package:flutter/material.dart';


class CraftDynamic extends StatelessWidget {
  final List<SimpleProperties> simplePropertiesList;
  final Function(ActionProperties) onAction;

  const CraftDynamic(
      {super.key, required this.simplePropertiesList, required this.onAction});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
        itemCount: simplePropertiesList.length,
        itemBuilder: (context, index) {
          final SimpleProperties simpleProperties = simplePropertiesList[index];
          final craftdBuilder =
              CraftDBuilderManager.getBuilder(simpleProperties.key);
          return craftdBuilder
              .craft(craftdBuilder.fromJson(simpleProperties.value),
                  (actionProperties) {
            onAction(actionProperties);
          });
        });
  }
}
