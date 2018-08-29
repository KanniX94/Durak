package de.htwg.se.durak.model

import de.htwg.se.durak.model.CardInterface.Card

import scala.collection.mutable.ArrayBuffer
import de.htwg.se.durak.model.{DeckInterface, Item}

case class Deck() extends DeckInterface[Item] {
  var cards = Array[Card](
    Card("7 Piek", 7, "P"),
    Card("8 Piek", 8, "P"),
    Card("9 Piek", 9, "P"),
    Card("10 Piek", 10, "P"),
    Card("Bube Piek", 11, "P"),
    Card("Dame Piek", 12, "P"),
    Card("Koenig Piek", 13, "P"),
    Card("Ass Piek", 14, "P"),
    Card("7 Herz", 7, "H"),
    Card("8 Herz", 8, "H"),
    Card("9 Herz", 9, "H"),
    Card("10 Herz", 10, "H"),
    Card("Bube Herz", 11, "H"),
    Card("Dame Herz", 12, "H"),
    Card("Koenig Herz", 13, "H"),
    Card("Ass Herz", 14, "H"),
    Card("7 Kreuz", 7, "K"),
    Card("8 Kreuz", 8, "K"),
    Card("9 Kreuz", 9, "K"),
    Card("10 Kreuz", 10, "K"),
    Card("Bube Kreuz", 11, "K"),
    Card("Dame Kreuz", 12, "K"),
    Card("Koenig Kreuz", 13, "K"),
    Card("Ass Kreuz", 14, "K"),
    Card("7 Caro", 7, "C"),
    Card("8 Caro", 8, "C"),
    Card("9 Caro", 9, "C"),
    Card("10 Caro", 10, "C"),
    Card("Bube Caro", 11, "C"),
    Card("Dame Caro", 12, "C"),
    Card("Koenig Caro", 13, "C"),
    Card("Ass Caro", 14, "C")
  )

  // Erstelle ein leeres Deck
  var deck = ArrayBuffer[Item]()
  var trumpCard = determineTrump()

  mixDeck()

  // Fuelle neues Deck mit Karten
  def fillDeck(): Unit = {
    deck.clear()
    deck ++= cards
  }

  // Mische das Deck
  def mixDeck(): Unit = {
    util.Random.shuffle(deck)
  }

  // Ermittle zufaelligen Trumpf
  def determineTrump(): Unit = {
    val r = scala.util.Random
    val tmp = r.nextInt(deck.length + 1)
    val trump = deck(tmp)
    deck.remove(tmp)
    trump
  }

  override def dealOut(): Item = {
    return null
  }

  def takeCard(item: Item): Item = {
    //Karte ziehen, bis alle Spieler 6 Karten auf der Hand haben
    // Oder lieber vom Player aus steuern?
    // ...
    return null
  }

  def dropCard(item: Item): Item = {
    return null
  }
}
