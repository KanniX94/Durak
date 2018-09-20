package de.htwg.se.durak.model

import de.htwg.se.durak.model.exactImpl.Card

import scala.collection.mutable.ArrayBuffer

// Item soll Oberklasse sein, da evtl spaeter noch extra Items oder so als Addon folgen koennen
// Jede Karte ist ein Item
trait Item {
  val name: String
}
