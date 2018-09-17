package de.htwg.se.durak.model

import de.htwg.se.durak.model.CardInterface.Card

import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Item] = null

  // Karte auf das Feld legen (ArrayBuffer fuellen und an naechsten Spieler uebergeben)
  def putCard(item: Item): Item

  // Karte schlagen
  def beatCard(item: Item): Item

  // Karte schieben
  def pushCard(item: Item): Item

  // Karten aufnehmen (wenn nicht geschlagen werden kann)
  def pickCard(item: Item): Item


}
