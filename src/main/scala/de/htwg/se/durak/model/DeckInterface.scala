package de.htwg.se.durak.model

import de.htwg.se.durak.model.exactImpl.Card

import scala.collection.mutable.ArrayBuffer

trait DeckInterface[Card] {
  // Karten an Spieler austeilen
  def dealOut(): Card

}