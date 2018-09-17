package de.htwg.se.durak.model

import scala.collection.mutable.ArrayBuffer

trait FieldInterface {
  var cardOnField: ArrayBuffer[exactImpl.Card] = null
  var players: ArrayBuffer[PlayerInterface] = null
  var amountOfPlayer: Int = 0
}
