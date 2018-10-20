package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer

case class Player(name: String) {
  override def toString: String = {
    name
  }

  var cardOnHand = ArrayBuffer[Card]()
}
