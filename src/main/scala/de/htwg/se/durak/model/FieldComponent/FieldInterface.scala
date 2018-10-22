package de.htwg.se.durak.model.FieldComponent

import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card, Player}
import scala.xml.{Elem, Node}
import scala.collection.mutable.ArrayBuffer

trait FieldInterface {

  var playerCardOnHand: ArrayBuffer[Card]
  var enemyCardOnHand: ArrayBuffer[Card]
  var deck: ArrayBuffer[Card]
  var cardOnField: ArrayBuffer[Card]
  var actualPlayer: Player
  var playerInGame: Array[Player]

  var win: Boolean
  var lose: Boolean

  def toXml: Elem

  def fromXml(node: Node)
}

trait DeckInterface[Card] {
  def isEmpty(): Boolean
}
