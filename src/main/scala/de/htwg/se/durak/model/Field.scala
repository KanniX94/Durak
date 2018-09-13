package de.htwg.se.durak.model

import scala.collection.mutable.ArrayBuffer

case class Field(p: Position) extends FieldInterface {
  players = ArrayBuffer[PlayerInterface]()
  cardOnField = ArrayBuffer[Item]()
}
