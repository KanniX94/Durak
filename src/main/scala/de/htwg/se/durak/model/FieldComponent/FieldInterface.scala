package de.htwg.se.durak.model.FieldComponent

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.Field
import scala.xml.{Elem, Node}

import scala.collection.mutable.ArrayBuffer

trait FieldInterface {
  var players: ArrayBuffer[PlayerInterface] = _
  var amountOfPlayer: Integer
  var field: Field

  def createNewGame

  def left(x: Integer)

  def right(x: Integer)

  def up(y: Integer)

  def down(y: Integer)

  def toXml: Elem

  def fromXml(node: Node)
}

trait DeckInterface[Card] {
  def isEmpty(): Boolean
}

trait PlayerInterface {
}
