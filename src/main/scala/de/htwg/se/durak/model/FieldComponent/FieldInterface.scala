package de.htwg.se.durak.model.FieldComponent

import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer

trait FieldInterface {
  var players: ArrayBuffer[PlayerInterface] = _
  var amountOfPlayer: Int = 0
  val length: Int = 3
}

trait DeckInterface[Card] {

}

trait PlayerInterface {

}
