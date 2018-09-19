package de.htwg.se.durak.model

import de.htwg.se.durak.model.exactImpl.Card

import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Card] = null
  var cardOnField: ArrayBuffer[Card] = null

  var actualHand: FieldInterface = null
  var actualField: FieldInterface = null
  val playingField: PlayingFieldInterface

  // Karte auf das Feld legen (ArrayBuffer fuellen und an naechsten Spieler uebergeben)
  def putCard(card: Card): Card

  // Karte schlagen
  def beatCard(card: Card): Card

  // Karte schieben
  def pushCard(card: Card): Unit = {
    //if (exactImpl.Deck.canPushCard(cardOnField.p.y, cardOnHand.p.y) {

    //}
  }

  // Karten aufnehmen (wenn nicht geschlagen werden kann)
  def pickCard(card: Card): Card


}
