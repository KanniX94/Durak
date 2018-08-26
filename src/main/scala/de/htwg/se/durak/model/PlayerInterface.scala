package de.htwg.se.durak.model
import scala.collection.mutable.ArrayBuffer

trait PlayerInterface {
  var cardOnHand: ArrayBuffer[Card] = null

  def beatCard: Boolean
  def pushCard: Boolean
  def takeCard: Boolean
  def dropCard: Card
}
