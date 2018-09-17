package de.htwg.se.durak.model

import de.htwg.se.durak.model.CardInterface.Card
import scala.collection.mutable.ArrayBuffer
import de.htwg.se.durak.model.{Item, PlayerInterface, Player, DeckInterface}

case class Player(name: String, cardOnField: ArrayBuffer[Card]) extends PlayerInterface {
  override def toString: String = name

  cardOnHand = ArrayBuffer[Card]()

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

  override def pushCard(card: Card): Card = ???

}

