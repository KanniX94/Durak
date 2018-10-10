package de.htwg.se.durak.model.FieldComponent

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card}
import de.htwg.se.durak.model.PlayerInterface

import scala.xml.{Elem, Node}
import scala.collection.mutable.ArrayBuffer

trait FieldInterface {

  var playerCardOnHand: ArrayBuffer[Card]
  var enemyCardOnHand: ArrayBuffer[Card]
  var deck: ArrayBuffer[Card]
  var cardOnField: ArrayBuffer[Card]
  var actualPlayer: PlayerInterface
  var playerInGame: Array[PlayerInterface]

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
