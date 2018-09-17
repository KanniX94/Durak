package de.htwg.se.durak.controller

import de.htwg.se.durak.model.CardInterface.Card
import de.htwg.se.durak.model.{Deck, PlayerInterface}

import scala.collection.mutable.ArrayBuffer

class ControllerInterface {
  var player: Array[PlayerInterface] = null
  var playerName: Array[String] = Array()
  var amountOfPlayer = 0
  var deck: ArrayBuffer[Card] = null
  var fieldLength = 0




}
