package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl


import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.durak.controller.controllerComponent.{ControllerInterface, FieldChanged}
import de.htwg.se.durak.controller.controllerComponent.GameStatus._
import de.htwg.se.durak.durakGameModule
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card, Deck, Player}
import de.htwg.se.durak.model.fileIoComponent.FileIoInterface
import play.api.libs.json.JsValue
import de.htwg.se.durak.util.UndoManager

import scala.util.{Failure, Success}
import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.durak.model.FieldComponent.FieldInterface

import scala.collection.mutable.ArrayBuffer

class Controller() extends ControllerInterface with LazyLogging {

  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager
  val injector = Guice.createInjector(new durakGameModule)
  val fileIo = injector.instance[FileIoInterface]

  val r = scala.util.Random
  var playerInGame: Array[Player] = _
  var playerName: Array[String] = Array("Bernd", "CPU1")
  var amountOfPlayer = 2
  var actualPlayer: Player = setActualPlayer()

  val allCards = 31
  var cardsLeft = 0

  var cardOnField: ArrayBuffer[Card] = _
  var deck = Deck.instance()

  def initialize(): Unit = {
    deck.init()
    cardsLeft = allCards + 1
    mixDeck(determineMixedDeck(), deck.deck.length)
    playerInGame = new Array[Player](amountOfPlayer)
    for (player <- 0 to playerInGame.length - 1) {
      playerInGame(player) = Player(playerName(player))
    }
    dealOut()
    setActualPlayer()
    printPlayerState()
  }

  def createEmptyField: Unit = {
    injector.instance[FieldInterface](Names.named("Feld"))
    publish(new FieldChanged)
  }

  def createNewField: Unit = {
    injector.instance[FieldInterface](Names.named("Feld"))
    gameStatus = NEW
    field = field.createNewField
    publish(new FieldChanged)
  }


  def doAction(field: FieldInterface, key: String): Unit = {
    doOtherActions(field, key)

    if (!field.win && !field.lose) {
      doGameAction(field, key)
    }
  }

  def doOtherActions(field: FieldInterface, key: String): Unit = {
    key match {
      case "speichern" => {
        fileIo.save("save.durak", field)
        gameStatus = SAVED
      }
      case "laden" => {
        fileIo.load("save.durak", field)
        gameStatus = LOADED
      }
      case "undo" => {
        fileIo.load("undo.durak", field)
        gameStatus = UNDO
      }
      case "beenden" =>
        sys.exit()
      case _ => {
        fileIo.save("undo.durak", field)
        gameStatus = REDO
      }
    }
  }

  def doGameAction(field: FieldInterface, key: String): Unit = {
    key match {
      case "links" => field.left()
      case "rechts" => field.right()
      case "schieben" => {
        field.push()
        gameStatus = PUSH
      }
      case "schlagen" => {
        field.beat()
        gameStatus = BEAT
      }
      case "schlucken" => {
        field.pull()
        gameStatus = PULL
      }
      case "non" => {
        field.non()
        gameStatus = NON
      }
      case "angreifen" => {
        field.attack()
        gameStatus = ATTACK
      }
      case _ =>

    }
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

  def pullCard: Unit = {
    actualPlayer.cardOnHand ++= cardOnField
    cardOnField.clear()
    print("Karten wurden aufgenommen!\n")
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

  def chooseCardOnHand(): Unit = {
    print("Welche Karte moechtest du auswaehlen?\n")
    for (i <- 0 to playerInGame(0).cardOnHand.length - 1) {
      print(i + 1 + " = " + playerInGame(0).cardOnHand(i).name + " | ")
    }
  }

  // Computergegner soll angreifen
  def cpuBeat(): Unit = {
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
        if (canBeatCard(cardOnField.last, card)) {
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

  def fieldToString: String = field.toString

  def toJson: JsValue = ???

}