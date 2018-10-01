package de.htwg.se.durak.model.FieldComponent

import de.htwg.se.durak.model.{PlayerInterface, PositionInterface}

import scala.collection.mutable.ArrayBuffer

trait FieldInterface {
  val p: PositionInterface
  var players: ArrayBuffer[PlayerInterface] = null
  var amountOfPlayer: Int = 0
  val length: Int = 3
}
