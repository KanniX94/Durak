package de.htwg.se.durak.model

import de.htwg.se.durak.model.CardInterface.Card

import scala.collection.mutable.ArrayBuffer

trait DeckInterface[Card] {
  var trumpCard: Card

  // Karten an Spieler austeilen (am Anfang eines Spiels)
  def dealOut(): Item

  // Karten werden vom Spieler aufgezogen (bis 6 Stueck)
  def takeCard(item: Item): Item

  // Karte wird beim Austeilen an Spieler aus dem Deck entfernt
  def dropCard(item: Item): Item

  def pickCard(item: Item): Item

  // Sind alle Karten auf dem Spielfeld gleich?
  def canPushCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean

  // Kann aktuelle Karte geschlagen werden?
  def canBeatCard(cardFromField: Card, cardFromHand: Card): Boolean

  // Kann ein Gegner seine Karte dazu legen?
  def canLayCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean
}