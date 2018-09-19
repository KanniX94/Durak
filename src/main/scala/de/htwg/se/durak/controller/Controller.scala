package de.htwg.se.durak.controller

import de.htwg.se.durak.model._
import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.util.Observable

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Controller extends ControllerInterface {

  cardOnField = new ArrayBuffer()
  beatenCard = new ArrayBuffer()
  deck = new Deck()
  
  def initialize(amountOfPlayer: Int): Unit = {
    this.amountOfPlayer = amountOfPlayer
    playerInGame = new Array[PlayerInterface](amountOfPlayer)
    GameReset()
  }

  def GameReset(): Unit = {
    actualPlayer = playerInGame(0)
  }

  def dealOut(): Unit = {
    for (i <- playerInGame) {
      while (i.cardOnHand.length < 6 && !deck.isEmpty()) {
        i.cardOnHand.append(deck.dealOut())
      }
    }
  }

  def printPlayerState(): Unit = {
    for (player <- playerInGame) {
      print(player.toString)
      for (card <- player.cardOnHand)
        print(" " + card)
    }
  }

}
