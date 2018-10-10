package de.htwg.se.durak.util

object Utils {
  def processKey(k1: Int, k2: Char): String = {
    if (k1 == 87 || k2 == 'w') {
      "schlagen"
    } else if (k1 == 65 || k2 == 'a') {
      "links"
    } else if (k1 == 83 || k2 == 's') {
      "schieben"
    } else if (k1 == 68 || k2 == 'd') {
      "rechts"
    } else if (k1 == 78 || k2 == 'n') {
      "non"
    } else if (k1 == 81 || k2 == 'q') {
      "beenden"
    } else if (k1 == 90 || k2 == 'z') {
      "speichern"
    } else if (k1 == 76 || k2 == 'l') {
      "laden"
    } else if (k1 == 85 || k2 == 'u') {
      "undo"
    } else if (k1 == 82 || k2 == 'r') {
      "redo"
    } else if (k1 == 80 || k2 == 'p') {
      "schlucken"
    } else if (k1 == 13) {
      "angreifen"
    } else {
      ""
    }
  }
}