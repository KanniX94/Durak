package de.htwg.se.durak.model

import scala.collection.mutable.ArrayBuffer

trait FieldInterface {
  var cardOnField: ArrayBuffer[Item] = null
  var players: ArrayBuffer[PlayerInterface] = null
  var amountOfPlayer: Int = 0
  val p: PositionInterface
}
