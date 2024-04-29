import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:craftd_module/data/model/base/simple_properties.dart';
import 'package:mobx/mobx.dart';

import 'main_state.dart';

part 'main_controller.g.dart';

class MainController extends MainControllerBase with _$MainController {
  MainController();
}

abstract class MainControllerBase with Store {
  MainControllerBase();

  @observable
  MainState state = MainLoadingState();

  @action
  Future<void> loadProperties(BuildContext context) async {
    await DefaultAssetBundle.of(context)
        .loadString("assets/dynamic.json")
        .then((value) {
      List<dynamic> list = jsonDecode(value);

      state = MainSuccessState(
          properties: list.map((properties) {
        return SimpleProperties.fromJson(properties);
      }).toList());
    }).catchError((error, stacktrace) {
      print(stacktrace.toString());
      state = MainErrorState(msg: stacktrace.toString());
    });
  }
}
