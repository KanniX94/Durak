package de.htwg.se.durak.controller

import de.htwg.se.durak.model._
import de.htwg.se.durak.model.exactImpl._

import de.htwg.se.durak.util.Observable

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Controller extends ControllerInterface {

  cardOnField = new ArrayBuffer()
  deck = new Deck()
  actualPlayer = Player(playingField, "Default")


  def initialize(amountOfPlayer: Int): Unit = {
    this.amountOfPlayer = amountOfPlayer
    playerInGame = new Array[PlayerInterface](amountOfPlayer)
  }

}
