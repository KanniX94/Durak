package de.htwg.se.durak.model.FieldComponent.FieldBaseImpl

import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer

case class Player(name: String) extends PlayerInterface {
  override def toString: String = {
    name
  }

  var cardOnHand = ArrayBuffer[Card]()

  def putCard(card: Card): Card = null

  def pickCard(card: Card): Card = {
    return null
  }

  def dropCard(card: Card): Card = {
    for (i <- 0 to cardOnHand.length - 1) {
      if (cardOnHand(i).name == card.name) {
        val tmpCard = cardOnHand(i)
        cardOnHand.remove(i)
        tmpCard
      }
    }
    return null
  }

  def beatCard(card: Card): Card = {
    null
  }

  def pushCard(card: Card): Unit = {

  }
}
