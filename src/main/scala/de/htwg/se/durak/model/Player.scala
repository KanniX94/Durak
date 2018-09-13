package de.htwg.se.durak.model

import de.htwg.se.durak.model.CardInterface.Card
import scala.collection.mutable.ArrayBuffer
import de.htwg.se.durak.model.{Item, PlayerInterface, Player}

case class Player(name: String) extends PlayerInterface {
  override def toString: String = name

  cardOnHand = ArrayBuffer[Item]()

  def win(): String = {
    return s"$name hat gewonnen und verlaesst das Spiel!"
  }

  def loose(): String = {
    return s"$name ist ein Durak!"
  }

  def putCard(item: Item): Item = ???

  def pushCard(item: Item): Item = {
    //Was passiert, wenn Karte geschoben wurde?
    // ...
    for (i <- 0 to cardOnHand.length - 1) {
      for (j <- 0 to cardOnField.length - 1) {
        if (cardOnHand(i).asInstanceOf[Card].value == item.asInstanceOf[Card].value) {
          // Karte mit gleichem Wert wurde gelegt
          // Karten bzw. temporaerer Buffer mit gelegten Karten wird an naechsten Player uebertragen
          // ...

          return null // Hier einen ArrayBuffer returnen, um gespielte Karten zum naechsten Spieler zu uebergeben
        }
      }
    }

    dropCard(card)
    return "Mit der Karte kannst du nicht schieben!"
  }

  def beatCard(card: Card): Card = {
    //Was passiert, wenn Karte geschlagen wurde?
    // ...
    for (i <- 0 to cardOnHand.length - 1) {
      if (cardOnHand(i).asInstanceOf[Card].value > card.value) {
        // Karte wurde mit hoeher wertiger Karte geschlagen

      }
    }

    dropCard(card)
    return null
  }

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
}

