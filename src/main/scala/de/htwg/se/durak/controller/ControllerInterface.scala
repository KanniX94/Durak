package de.htwg.se.durak.controller

import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.model._

import scala.collection.mutable.ArrayBuffer

trait  ControllerInterface {
  var playerInGame: Array[PlayerInterface] = null
  var playerName: Array[String] = Array()
  var amountOfPlayer = 0

  var deck: exactImpl.Deck = null
  var cardOnField: ArrayBuffer[Card] = null

  var fieldLength = 0


  def initialize(amountOfPlayer: Int)



}
