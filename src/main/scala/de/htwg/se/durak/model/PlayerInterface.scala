package de.htwg.se.durak.model
import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Item] = null

  // Karte schlagen
  def beatCard(item: Item): Item
  // Karte schieben
  def pushCard(item: Item): Item
}
