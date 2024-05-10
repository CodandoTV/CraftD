import 'package:equatable/equatable.dart';
import 'package:craftd_widget/craftd_widget.export.dart';

class MainState extends Equatable {
  @override
  List<Object?> get props => [];
}

class MainLoadingState extends MainState {}

class MainSuccessState extends MainState {
  final List<SimpleProperties> properties;

  MainSuccessState({required this.properties});
}

class MainErrorState extends MainState {
  final String msg;

  MainErrorState({required this.msg});
}


extension UserStateMapper on MainState {
  MainSuccessState toStateSuccess() => this as MainSuccessState;
  MainErrorState toStateError() => this as MainErrorState;
}