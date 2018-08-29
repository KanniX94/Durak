package de.htwg.se.durak.model
import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Item] = null

  def beatCard(item: Item): Item
  def pushCard(item: Item): Item
}
