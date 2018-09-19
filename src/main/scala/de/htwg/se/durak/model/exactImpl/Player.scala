package de.htwg.se.durak.model.exactImpl

import de.htwg.se.durak.model.{Item, PlayerInterface, PlayingFieldInterface, PositionInterface}

import scala.collection.mutable.ArrayBuffer

case class Player(playingField: PlayingFieldInterface, name: String) extends PlayerInterface {
  override def toString: String = name

  cardOnHand = ArrayBuffer[Card]()
  cardOnField = ArrayBuffer[Card]()

  def getCardOnField(cards: ArrayBuffer[Card]): Unit = {
    cardOnField = cards
  }

  def moveCardOnField(): Unit = cardOnField.clear()

  def win(): String = {
    return s"$name hat gewonnen und verlaesst das Spiel!"
  }

  def loose(): String = {
    return s"$name ist ein Durak!"
  }

  def putCard(card: Card): Card = ???

  def pickCard(card: Card): Card = {
    return null
  }

  def dropCard(card: Card): Item = {
    for (i <- 0 to cardOnHand.length - 1) {
      if (cardOnHand(i).name == card.name) {
        val tmpCard = cardOnHand(i)
        cardOnHand.remove(i)
        tmpCard
      }
    }
    return null
  }

  override def beatCard(card: Card): Card = ???

  override def pushCard(card: Card): Unit = {

  }

  override val p: PositionInterface = null
}

