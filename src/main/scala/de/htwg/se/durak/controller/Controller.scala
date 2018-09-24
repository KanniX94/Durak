package de.htwg.se.durak.controller

import de.htwg.se.durak.model._
import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.util.Observable

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.swing.event.Event
import scala.swing.Publisher

case class Difficulty()

case class Start()

case class StartRound()

case class GameStart()

case class GameNew()

case class AmountPlayer()

case class AttackPlayer()

case class PushCard()

case class BeatCard()

case class PutCard()

case class GameLost()

case class GameWon()


class Controller extends ControllerInterface {

  cardOnField = new ArrayBuffer()
  beatenCard = new ArrayBuffer()
  deck = new Deck()

  def initialize(amountOfPlayer: Int): Unit = {
    deck.init()
    this.amountOfPlayer = amountOfPlayer
    playerInGame = new Array[PlayerInterface](amountOfPlayer)
    for (player <- 0 to playerInGame.length - 1) {
      playerInGame(player) = Player(playerName(player))
    }
    dealOut()
    printPlayerState()
    gameReset()
  }

  def gameReset(): Unit = {
    dealOut()
    actualPlayer = playerInGame(0)
  }

  def dealOut(): Unit = {
    for (i <- playerInGame) {
      while (i.cardOnHand.length < 6 && !deck.isEmpty()) {
        i.cardOnHand.append(deck.dealOut())
        cardsLeft -= 1
      }
    }
  }

  def printPlayerState(): Unit = {
    cheat()
    /*print(playerInGame(0).toString + "\n")
    for (card <- playerInGame(0).cardOnHand) {
      print(card.name + "\n")
    }*/
  }

  def cheat(): Unit = {
    for (player <- playerInGame) {
      print(player.toString + "\n")
      for (card <- player.cardOnHand)
        print(card.name + "\n")
      print("\n")
    }
  }

  def beatCard(attackCard: Int, beatCard: Int): Unit = {
    if (attackCard <= cardOnField.length - 1) {
      if (deck.canBeatCard(cardOnField(attackCard), actualPlayer.cardOnHand(beatCard))) {
        beatenCard.append(cardOnField(attackCard))
        beatenCard.append(actualPlayer.cardOnHand(beatCard))
        cardOnField.remove(cardOnField.indexOf(cardOnField(attackCard)))
      }
    }
  }

  def setDifficulty(dif: Int): Unit = {
    difficulty = dif
  }

  def attack(card: Card): Unit = {
    cardOnField.append(card)
  }

  def cpuAttacks(): Unit = {
    val r = scala.util.Random
    val attackChance = r.nextInt(3) + 1
    difficulty match {
      case 2 => {
        for (cpuPlayer <- 1 to playerInGame.length + 1) {
          for (i <- playerInGame(cpuPlayer).cardOnHand)
            if (attackChance <= 2 && deck.canLayCard(cardOnField, i)) {
              attack(i)
            }
        }
      }
      case 3 => {
        for (cpuPlayer <- 1 to playerInGame.length + 1) {
          for (i <- playerInGame(cpuPlayer).cardOnHand) {
            if (deck.canLayCard(cardOnField, i)) {
              attack(i)
            }
          }
        }
      }
    }
  }
}
