package de.htwg.se.durak.model.exactImpl

import de.htwg.se.durak.model.{FieldInterface, Item, PlayerInterface}

import scala.collection.mutable.ArrayBuffer

case class Field(p: Position) extends FieldInterface {
  players = ArrayBuffer[PlayerInterface]()
}
