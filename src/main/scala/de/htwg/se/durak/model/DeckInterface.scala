package de.htwg.se.durak.model

import de.htwg.se.durak.model.exactImpl.Card

import scala.collection.mutable.ArrayBuffer

trait DeckInterface[Card] {
  var trumpCard: Card

  // Karten an Spieler austeilen
  def dealOut(): Card

  // Sind alle Karten auf dem Spielfeld gleich?
  def canPushCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean

  // Kann aktuelle Karte geschlagen werden?
  def canBeatCard(cardFromField: Card, cardFromHand: Card): Boolean

  // Kann ein Gegner seine Karte dazu legen?
  def canLayCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean
}