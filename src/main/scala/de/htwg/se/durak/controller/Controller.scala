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

  actualPlayer = Player(playingField, "Default")


  def initialize(amountOfPlayer: Int): Unit = {
    this.amountOfPlayer = amountOfPlayer
    playerInGame = new Array[PlayerInterface](amountOfPlayer)
    playingField = new PlayingField(4, 32)
    fieldLength = playingField.line(0)(0).length
    GameReset()
  }

  def GameReset(): Unit = {
    actualPlayer = playerInGame(0)
  }

  def dealOut(): Unit = {
    for (i <- playerInGame) {
      if (i.actualHand.length < 6 && !deck.isEmpty()) {
        i.cardOnHand.append(deck.dealOut())
      }
    }
  }

}
