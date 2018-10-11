package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import scala.io.StdIn.readLine
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

import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.durak.model.FieldComponent.FieldInterface

import scala.collection.mutable.ArrayBuffer

object Controller extends ControllerInterface with LazyLogging {

  //var gameStatus: GameStatus = IDLE
  //private val undoManager = new UndoManager
  val scanner = new java.util.Scanner(System.in)
  val injector = Guice.createInjector(new durakGameModule)
  val fileIo = injector.instance[FileIoInterface]

  val r = scala.util.Random
  var playerInGame: Array[Player] = _
  var playerName: Array[String] = _
  var beatenCard: ArrayBuffer[Card] = _
  var amountOfPlayer = 2
  var actualPlayer: Player = setActualPlayer()
  playerInGame = new Array[Player](amountOfPlayer)
  var trumpCard: Card = determineTrump
  var difficulty = 0

  val allCards = 31
  var cardsLeft = 0

  var cardOnField: ArrayBuffer[Card] = _
  var deck = Deck.instance()

  def initialize(): Unit = {
    determinePlayer()
    setDifficulty()
    deck.init()
    cardsLeft = allCards + 1
    mixDeck(determineMixedDeck(), deck.deck.length)
    dealOut()
    setActualPlayer()
    printPlayerState()
  }

  def determinePlayer(): Unit = {
    print("Spieler benennen..\n")
    var line = scanner.nextLine()
    for (amount <- 0 to amountOfPlayer - 1) {
      print("Wie soll der " + amount + 1 + ". Spieler heissen?")
      line = scanner.nextLine()
      playerName(amount) = line
    }
    for (player <- 0 to playerInGame.length - 1) {
      playerInGame(player) = Player(playerName(player))
    }
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
      }
      case "laden" => {
        fileIo.load("save.durak", field)
      }
      case "undo" => {
        fileIo.load("undo.durak", field)
      }
      case "beenden" =>
        sys.exit()
      case _ => {
        fileIo.save("undo.durak", field)
      }
    }
  }

  def doGameAction(field: FieldInterface, key: String): Unit = {
    key match {
      case "links" => field.left()
      case "rechts" => field.right()
      case "schieben" => {
        field.push()
      }
      case "schlagen" => {
        field.beat()
      }
      case "schlucken" => {
        field.pull()
      }
      case "non" => {
        field.non()
      }
      case "angreifen" => {
        field.attack()
      }
      case _ =>
    }
  }

  // Aktueller Spieler wird ausgewaehlt, damit dieser schlagen, schieben, etc. kann
  def setActualPlayer(): Player = {
    val r = scala.util.Random
    playerInGame(r.nextInt(playerInGame.length))
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

  def toJson: JsValue = ???

  // Karten an alle Spieler ausgeben (vor jeder Runde)
  def dealOut(): Unit = {
    for (i <- playerInGame) {
      while (i.cardOnHand.length < 6 && !deck.isEmpty()) {
        i.cardOnHand.append(deck.dealOut())
        cardsLeft -= 1
      }
    }
  }

  // Ausgewaehlte Karte wird geschlagen und temporaer gespeichert werden
  def beatCard(attackCard: Int, beatCard: Int): Unit = {
    if (attackCard <= cardOnField.length - 1) {
      if (canBeatCard(cardOnField(attackCard), actualPlayer.cardOnHand(beatCard))) {
        beatenCard.append(cardOnField(attackCard))
        beatenCard.append(actualPlayer.cardOnHand(beatCard))
        cardOnField.remove(cardOnField.indexOf(cardOnField(attackCard)))
      }
    }
  }

  // Schwierigkeitsgrad fuer den Computergegner
  def setDifficulty(): Unit = {
    print("Wie agressiv sollen die Gegner sein? (1 - 3)\n")
    var line = scanner.nextLine()
    while (line.toInt < 1 || line.toInt > 3) {
      line = scanner.nextLine()
    }
    difficulty = line.toInt
  }

  def attack(card: Card): Unit = {
    cardOnField.append(card)
  }

  // Computergegner soll je nach schwierigkeitsgrad angreifen
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
  def mixDeck(list: List[Int], count: Int): Unit = {
    for (i <- 0 to count - 1) {
      for (j <- list) {
        val tmpCard = deck.deck(i)
        deck.deck(i) = deck.deck(j)
        deck.deck(j) = tmpCard
      }
    }
  }

  def determineMixedDeck(): List[Int] = r.shuffle(0.to(allCards).toList)

  // Ermittle zufaelligen Trumpf
  def determineTrump(): Card = {
    val r = scala.util.Random
    val tmp = r.nextInt(deck.deck.length - 1)
    deck.deck(tmp)
  }


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
}