package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl


import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.controllerComponent.GameStatus._
import de.htwg.se.durak.durakGameModule
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card, Deck, Player}
import de.htwg.se.durak.model._
import de.htwg.se.durak.model.fileIoComponent.FileIoInterface
import de.htwg.se.durak.util.Observable
import play.api.libs.json.JsValue
import de.htwg.se.durak.util.UndoManager

import scala.util.{Failure, Success}
import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.durak.model.FieldComponent.FieldInterface

import scala.collection.mutable.ArrayBuffer
import scala.swing.{Reactions, RefSet}
import scala.swing.event.Event

class Controller @Inject()(var field: FieldInterface) extends ControllerInterface {

  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new durakGameModule)
  val fileIo = injector.instance[FileIoInterface]

  val r = scala.util.Random
  var playerInGame: Array[Player] = _
  var playerName: Array[String] = Array("Marcel", "CPU1")
  var amountOfPlayer = 2
  var actualPlayer: Player = setActualPlayer()

  val allCards = 31
  var cardsLeft = 0

  var cardOnField: ArrayBuffer[Card] = _
  var deck = new Deck()

  def initialize(amountOfPlayer: Int): Unit = {
    deck.init()
    cardsLeft = allCards + 1
    mixDeck(determineMixedDeck(), deck.deck.length)
    this.amountOfPlayer = amountOfPlayer
    playerInGame = new Array[Player](amountOfPlayer)
    for (player <- 0 to playerInGame.length - 1) {
      playerInGame(player) = Player(playerName(player))
    }
    gameReset()
    printPlayerState()
  }

  def createEmptyField: Unit = {
    injector.instance[FieldInterface](Names.named("Feld"))
    publish(new FieldChanged)
  }

  // Spiel wird auf Anfangszustand zurueckgesetzt
  def gameReset(): Unit = {
    dealOut()
    setActualPlayer()
  }

  // Aktueller Spieler wird ausgewaehlt, damit dieser schlagen, schieben, etc. kann
  def setActualPlayer(): Player = {
    val r = scala.util.Random
    playerInGame(r.nextInt(playerInGame.length))
  }

  // Karten an alle Spieler ausgeben (vor jeder Runde)
  def dealOut(): Unit = {
    for (i <- playerInGame) {
      while (!deck.isEmpty() && deck.deck.length % 16 != 0) {
        i.cardOnHand.append(deck.dealOut())
        cardsLeft -= 1
      }
    }
  }

  // Karten des menschlichen Spielers ausgeben
  def printPlayerState(): Unit = {
    print(playerInGame(0).toString + "\n")
    for (card <- playerInGame(0).cardOnHand) {
      print(card.name + "\n")
    }
  }

  def attack(card: Card): Unit = {
    cardOnField.append(card)
  }

  // Computergegner soll angreifen
  def cpuBeat(cardFromField: Card): Unit = {
    if (cardOnField.isEmpty) {
      var lowestCard: Card = Card("Joker", allCards, "j")
      for (card <- playerInGame(1).cardOnHand) {
        if (card.value < lowestCard.value) {
          lowestCard = card
        }
      }
      cardOnField.append(lowestCard)
    } else {
      for (card <- playerInGame(1).cardOnHand) {
        if (canBeatCard(cardFromField, card)) {
          cardOnField.append(card)
        }
        else {
          for (card <- cardOnField) {
            playerInGame(1).cardOnHand.append(card)
          }
        }
      }
    }
  }

  // Kann die Karte geschlagen werden?
  def canBeatCard(cardFromField: Card, cardFromHand: Card): Boolean = {
    if (cardFromHand.value > cardFromField.value) {
      true
    } else {
      false
    }
  }

  // Mische das Deck
  def mixDeck(list: List[Int], count: Int): Unit = {
    for (i <- 0 until count - 1) {
      for (j <- list) {
        val tmpCard = deck.deck(i)
        deck.deck(i) = deck.deck(j)
        deck.deck(j) = tmpCard
      }
    }
  }

  def determineMixedDeck(): List[Int] = r.shuffle(0.to(allCards).toList)

  // ONLY FOR CHEATING!

  def cheat(): Unit = {
    printEnemyHand()
    printDeck()
  }

  def printDeck(): Unit = {
    print("Im aktuellen Deck sind noch folgende Karten\n")
    for (i <- deck.deck) {
      print(i.name + "\n")
    }
  }

  def printEnemyHand(): Unit = {
    print("Die Spieler haben folgende Karten auf der Hand\n")
    for (player <- playerInGame) {
      print(player.toString + "\n")
      for (card <- player.cardOnHand)
        print(card.name + "\n")
      print("\n")
    }
  }

  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new FieldChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new FieldChanged)
  }

  def save: Unit = {
    fileIo.save(field) match {
      case Success(_) =>
        gameStatus = SAVED
      case Failure(e) =>
        logger.error(
          "Beim Speichern ist ein Fehler aufgetreten: " + e.getMessage)
        gameStatus = COULD_NOT_SAVE
    }

    publish(new FieldChanged)
  }

  def load: Unit = {
    val gridOptionResult = fileIo.load

    gridOptionResult match {
      case Success(gridOption) =>
        gridOption match {
          case Some(_field) =>
            field = _field
            gameStatus = LOADED
          case None =>
            createEmptyField
            gameStatus = COULD_NOT_LOAD
        }
      case Failure(e) =>
        logger.error(
          "Beim Laden ist ein Fehler aufgetreten: " + e.getMessage)
        createEmptyField
        gameStatus = COULD_NOT_LOAD
    }

    publish(new FieldChanged)
  }

  def toJson: JsValue = _
}
