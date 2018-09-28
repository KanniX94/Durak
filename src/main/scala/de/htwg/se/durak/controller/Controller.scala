package de.htwg.se.durak.controller

import de.htwg.se.durak.model._
import de.htwg.se.durak.model.exactImpl._
import de.htwg.se.durak.util.Observable

import scala.collection.mutable.ArrayBuffer
import scala.swing.event.Event

case class Difficulty()

case class Start()

case class GameStart()

case class mainMenu() extends Event

case class GameNew()

case class saveGame() extends Event

case class loadGame() extends Event

case class AmountPlayer() extends Event

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
  override var trumpCard: Card = determineTrump()

  def initialize(amountOfPlayer: Int): Unit = {
    publish(new AmountPlayer)
    deck.init()
    mixDeck()
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
    setActualPlayer()
  }

  def setActualPlayer(): Unit = {
    val r = scala.util.Random
    actualPlayer = playerInGame(r.nextInt(playerInGame.length))
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
    print(playerInGame(0).toString + "\n")
    for (card <- playerInGame(0).cardOnHand) {
      print(card.name + "\n")
    }
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
      if (canBeatCard(cardOnField(attackCard), actualPlayer.cardOnHand(beatCard))) {
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
            if (attackChance <= 2 && canLayCard(cardOnField, i)) {
              attack(i)
            }
        }
      }
      case 3 => {
        for (cpuPlayer <- 1 to playerInGame.length + 1) {
          for (i <- playerInGame(cpuPlayer).cardOnHand) {
            if (canLayCard(cardOnField, i)) {
              attack(i)
            }
          }
        }
      }
    }
  }


  // Sind alle Karten auf dem Feld gleich, sodass geschoben werden kann?
  def canPushCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean = {
    var equalCards = cardOnField(0).value
    for (i <- 1 to cardOnField.length - 1) {
      if (cardOnField(i).value != equalCards) {
        false
      }
      equalCards = cardOnField(i + 1).value
    }
    true
  }

  // Kann die Karte geschlagen werden?
  def canBeatCard(cardFromField: Card, cardFromHand: Card): Boolean = {
    if ((cardFromHand.value > cardFromField.value &&
      cardFromHand.symbol == cardFromField.symbol) ||
      (!isTrump(cardFromField) && isTrump(cardFromHand)) ||
      (isTrump(cardFromHand) && isTrump(cardFromField) &&
        cardFromHand.value > cardFromField.value)) {
      true
    } else {
      false
    }
  }

  // Kann ein Gegner seine Karte dazu legen?
  def canLayCard(cardOnField: ArrayBuffer[Card], cardFromHand: Card): Boolean = {
    for (i <- 0 to cardOnField.length - 1) {
      if (cardOnField(i).value != cardFromHand.value) {
        false
      }
    }
    true
  }

  // Ist gelegte Karte eine Trumpfkarte?
  def isTrump(card: Card): Boolean = if (card.symbol == trumpCard.symbol) true else false


  // Mische das Deck
  def mixDeck(): Unit = {
    val r = scala.util.Random
    for (i <- deck.deck.indices) {
      val tmp = r.nextInt(deck.deck.length + 1)
      val tmpCard = deck.deck(i)
      deck.deck(i) = deck.deck(tmp)
      deck.deck(tmp) = tmpCard
    }
  }

  // Ermittle zufaelligen Trumpf
  def determineTrump(): Card = {
    val r = scala.util.Random
    val tmp = r.nextInt(deck.deck.length - 1)
    deck.deck(tmp)
  }
}
