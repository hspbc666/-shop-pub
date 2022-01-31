import 'package:flutter/material.dart';

mySpacer(double padding) {
  return Container(
    margin: EdgeInsets.only(top: padding),
  );
}

myVerticalSpacer(double padding) {
  return Container(
    margin: EdgeInsets.only(left: padding),
  );
}

Divider defaultDivider() {
  return const Divider(
    height: 10,
    thickness: 1,
  );
}

emptyContainer() {
  return Container();
}
