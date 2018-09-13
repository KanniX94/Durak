package de.htwg.se.durak.model
import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Item] = null

  // Karte auf naechsten Spieler legen
  def putCard(item: Item): Item
  // Karte schlagen
  def beatCard(item: Item): Item
  // Karte schieben
  def pushCard(item: Item): Item
  // Karten aufnehmen (wenn nicht geschlagen werden kann)
  def pickCard(item: Item): Item
}
