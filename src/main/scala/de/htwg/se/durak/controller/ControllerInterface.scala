package de.htwg.se.durak.controller

import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.model._

import scala.collection.mutable.ArrayBuffer

trait ControllerInterface {
  var playerInGame: Array[PlayerInterface]
  var playerName: Array[String] = Array()
  var amountOfPlayer = 0
  var actualPlayer: PlayerInterface

  var deck: exactImpl.Deck
  var cardOnField: ArrayBuffer[Card]
  var beatenCard: ArrayBuffer[Card]

  var difficulty = 0
  var cardsLeft = 0

  def initialize(amountOfPlayer: Int)
  def setDifficulty(dif: Int)


}
