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

  var win: Boolean
  var lose: Boolean

  def createNewGame

  def left()

  def right()

  def push()

  def beat()

  def pull()

  def attack()

  def non()

  def toXml: Elem

  def fromXml(node: Node)
}

trait DeckInterface[Card] {
  def isEmpty(): Boolean
}
