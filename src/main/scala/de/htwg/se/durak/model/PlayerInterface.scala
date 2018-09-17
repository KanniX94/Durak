package de.htwg.se.durak.model

import de.htwg.se.durak.model.exactImpl.Card

import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Card] = null

  // Karte auf das Feld legen (ArrayBuffer fuellen und an naechsten Spieler uebergeben)
  def putCard(card: Card): Card

  // Karte schlagen
  def beatCard(card: Card): Card

  // Karte schieben
  def pushCard(card: Card): Card

  // Karten aufnehmen (wenn nicht geschlagen werden kann)
  def pickCard(card: Card): Card


}
