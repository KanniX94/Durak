package de.htwg.se.durak.controller

import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.model._

import scala.collection.mutable.ArrayBuffer

trait ControllerInterface {
  var playerInGame: Array[PlayerInterface] = null
  var playerName: Array[String] = Array()
  var amountOfPlayer = 0
  var actualPlayer: PlayerInterface = null

  var deck: exactImpl.Deck = null
  var cardOnField: ArrayBuffer[Card] = null
  var beatenCard: ArrayBuffer[Card] = null

  var difficulty = 0

  def initialize(amountOfPlayer: Int)

  def setDifficulty(dif: Int): Unit = {
    difficulty = dif
  }

  def cpuAttacks(): Unit = {
    val r = scala.util.Random
    val attackChance = r.nextInt(3) + 1
    difficulty match {
      case 2 => {
        for (cpuPlayer <- 1 to playerInGame.length + 1) {
          for (i <- playerInGame(cpuPlayer).cardOnHand)
            if (attackChance <= 2 && deck.canLayCard(cardOnField, i)) {

            }
        }
      }
      case 3 => {
        for (cpuPlayer <- 1 to playerInGame.length + 1) {
          for (i <- playerInGame(cpuPlayer).cardOnHand)
            if (deck.canLayCard(cardOnField, i)) {

            }
        }
      }
    }

  }


}
