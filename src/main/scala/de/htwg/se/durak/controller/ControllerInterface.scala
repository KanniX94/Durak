package de.htwg.se.durak.controller

import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.model._

import scala.collection.mutable.ArrayBuffer
import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  var playerInGame: Array[PlayerInterface] = null
  var playerName: Array[String] = Array("Marcel", "Christoph", "Bernd", "Sabrina")
  var amountOfPlayer = 0
  var actualPlayer: PlayerInterface = null

  var deck: exactImpl.Deck = null
  var cardOnField: ArrayBuffer[Card] = null
  var beatenCard: ArrayBuffer[Card] = null

  var difficulty = 0
  var cardsLeft = 0

  def initialize(amountOfPlayer: Int)
  def setDifficulty(dif: Int)


}
