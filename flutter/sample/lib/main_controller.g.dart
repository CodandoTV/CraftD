// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'main_controller.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$MainController on MainControllerBase, Store {
  late final _$stateAtom =
      Atom(name: 'MainControllerBase.state', context: context);

  @override
  MainState get state {
    _$stateAtom.reportRead();
    return super.state;
  }

  @override
  set state(MainState value) {
    _$stateAtom.reportWrite(value, super.state, () {
      super.state = value;
    });
  }

  late final _$loadPropertiesAsyncAction =
      AsyncAction('MainControllerBase.loadProperties', context: context);

  @override
  Future<void> loadProperties(BuildContext context) {
    return _$loadPropertiesAsyncAction.run(() => super.loadProperties(context));
  }

  @override
  String toString() {
    return '''
state: ${state}
    ''';
  }
}
