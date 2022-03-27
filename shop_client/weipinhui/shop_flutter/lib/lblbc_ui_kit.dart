import 'package:flutter/material.dart';

lblColumnSpacer(double padding) {
  return Container(
    margin: EdgeInsets.only(top: padding),
  );
}

lblRowSpacer(double padding) {
  return Container(
    margin: EdgeInsets.only(left: padding),
  );
}

Divider lblDivider() {
  return const Divider(
    height: 10,
    thickness: 1,
  );
}

emptyContainer() {
  return Container();
}
