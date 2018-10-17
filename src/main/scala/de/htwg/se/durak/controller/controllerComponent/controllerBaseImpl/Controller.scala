package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import scala.io.StdIn.readLine
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.durakGameModule
import de.htwg.se.durak.model.FieldComponent.FieldBaseImpl.{Card, Deck, Player}
import de.htwg.se.durak.model.fileIoComponent.FileIoInterface
import play.api.libs.json.JsValue
import de.htwg.se.durak.util.UndoManager

import util.control.Breaks._
import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.model.PlayerInterface

import scala.collection.mutable.ArrayBuffer

class Controller extends ControllerInterface with LazyLogging {

  val scanner = new java.util.Scanner(System.in)
  //val injector = Guice.createInjector(new durakGameModule)
  //val fileIo = injector.instance[FileIoInterface]

  val r = scala.util.Random
  var playerInGame = new Array[Player](2)
  var beatenCard = ArrayBuffer.empty[Card]
  var amountOfPlayer = 2
  var playerName = new Array[String](amountOfPlayer)
  playerInGame = new Array[Player](amountOfPlayer)
  var actualPlayer: Player = _
  var difficulty = 0
  var canBeat = true
  var tmpCard = new ArrayBuffer[Card]

  val allCards = 31
  var cardsLeft = 0

  var cardOnField = ArrayBuffer.empty[Card]
  var deck = Deck.instance()
  var trumpCard: Card = determineTrump()
  var line = scanner.nextLine()
  var line2 = scanner.nextLine()

  def initialize(): Unit = {
    determinePlayer()
    actualPlayer = playerInGame(0) //setActualPlayer()
    setDifficulty()
    deck.init()
    cardsLeft = allCards + 1
    mixDeck(determineMixedDeck(), deck.deck.length)
    dealOut()
    setActualPlayer()
    gameLoop()
  }

  def determinePlayer(): Unit = {
    print("Spieler benennen..\n")
    for (amount <- 0 to amountOfPlayer - 1) {
      print("Wie soll der " + (amount + 1) + ". Spieler heissen?")
      line = scanner.nextLine()
      playerName(amount) = line
    }
    for (player <- 0 to playerInGame.length - 1) {
      playerInGame(player) = new Player(playerName(player))
    }
  }

  /*def doAction(field: FieldInterface, key: String): Unit = {
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
*/
  def gameLoop(): Unit = {
    print(trumpCard.name.split(" ").last + " ist Trumpf\n")
    while (true) {
      dealOut()
      print(actualPlayer.name + " ist an der Reihe!\n")
      printPlayerCards()
      if (actualPlayer == playerInGame(0)) {
        chooseCardOnHand()
        layFurtherCard()
        printCardOnField()
        canBeat = true
        changeActualPlayer(actualPlayer)
        while (canBeat) {
          cpuBeat()
          //changeActualPlayer(actualPlayer)
          printPlayerCards()
          layFurtherCardOnBeatenCards()
          changeActualPlayer(actualPlayer)
        }
      } else {
        canBeat = true
        while (canBeat && actualPlayer == playerInGame(1)) {
          cpuAttacks()
          changeActualPlayer(actualPlayer)
          printCardOnField()
          defend()
          if (!canBeat) {
            changeActualPlayer(actualPlayer)
          }
        }
      }
    }
  }

  def cantBeat(): Unit = {
    var canBeat = false
    for (cardFromField <- cardOnField if (canBeat)) {
      for (cardFromHand <- playerInGame(0).cardOnHand) {
        if (canBeatCard(cardFromField, cardFromHand)) {
          canBeat = true
        } else {
          canBeat = false
        }
      }
      if (!canBeat) {
        pullCard()
        break()
      }
    }
  }

  def defend(): Unit = {
    val tmpField = cardOnField
    canBeat = true
    breakable {
      while (canBeat && cardOnField.length > 0) {
        cantBeat()
        if (!canBeat) {
          break()
        }
        print("Welche Karte moechtest du schlagen? (Aufnehmen = pull)\n")
        for (cardFromField <- 0 to cardOnField.length - 1) {
          print(cardFromField + 1 + " = " + cardOnField(cardFromField).name + " | ")
        }
        print("\n")
        line = scanner.nextLine()
        if (line.matches("[-+]?\\d+(\\.\\d+)?")) {
          while (line.toInt < 1 && line.toInt > cardOnField.length + 1) {
            print("Diese Karte steht nicht zur Auswahl!\nProbiere es noch einmal..")
            line = scanner.nextLine()
          }
        }
        if (line == "pull") {
          pullCard()
          canBeat = false
          break()
        }
        print("Mit welcher Karte moechtest du schlagen?\n")
        for (cardFromHand <- 0 to playerInGame(0).cardOnHand.length - 1) {
          print(cardFromHand + 1 + " = " + playerInGame(0).cardOnHand(cardFromHand).name + " | ")
        }
        print("\n")
        line2 = scanner.nextLine()
        while (line2.toInt < 1 || line2.toInt > actualPlayer.cardOnHand.length + 1) {
          print("Du hast diese Karte nicht auf der Hand!\nProbiere es noch einmal..")
          line2 = scanner.nextLine()
        }
        if (canBeatCard(cardOnField(line.toInt - 1), playerInGame(0).cardOnHand(line2.toInt - 1))) {
          print("Du hast " + cardOnField(line.toInt - 1) + " mit " + playerInGame(0).cardOnHand(line2.toInt - 1) + " geschlagen!\n")
          beatenCard += cardOnField(line.toInt - 1)
          beatenCard += playerInGame(0).cardOnHand(line2.toInt - 1)
          cardOnField.remove(line.toInt - 1)
          playerInGame(0).cardOnHand.remove(line2.toInt - 1)
        } else {
          print("Du kannst " + cardOnField(line.toInt - 1).name + " nicht mit "
            + playerInGame(0).cardOnHand(line2.toInt - 1).name + " schlagen!\n")
        }
      }
    }
  }

  def layFurtherCardOnBeatenCards(): Unit = {
    if (playerInGame(1).cardOnHand.size > cardOnField.size) {
      for (beatenCard <- beatenCard) {
        layIt(beatenCard)
      }
    }
  }

  // Computergegner soll angreifen/abwehren
  def cpuBeat(): Unit = {
    val tmpField = cardOnField
    val tmpHand = playerInGame(1).cardOnHand
    breakable {
      for (cardFromField <- tmpField) {
        val tmpField = cardFromField
        canBeat = false
        for (cardFromHand <- tmpHand) {
          val tmpHand = cardFromHand
          if (canBeatCard(tmpField, tmpHand)) {
            print(playerInGame(1).name + " hat " + tmpField + " mit " + tmpHand + " geschlagen!\n")
            beatenCard.append(tmpHand)
            beatenCard.append(tmpField)
            cardOnField.remove(cardOnField.indexOf(tmpField))
            canBeat = true
            playerInGame(1).cardOnHand.remove(playerInGame(1).cardOnHand.indexOf(tmpHand))
            break()
          }
        }
        if (!canBeat) {
          pullCard()
          break()
        }
      }
    }
    if (canBeat) {
      print(actualPlayer.name + " konnte alle Karten schlagen.\n")
    }
    changeActualPlayer(actualPlayer)

  }

  def layFurtherCard(): Unit = {
    if (playerInGame(1).cardOnHand.size > cardOnField.size) {
      tmpCard.clear()
      tmpCard ++= cardOnField
      tmpCard ++= beatenCard
      for (cardFromField <- tmpCard) {
        layIt(cardFromField)
      }
    }
  }

  def layIt(otherCard: Card): Unit = {
    val tmpOtherCard = otherCard
    val tmp = playerInGame(0).cardOnHand

    for (card <- tmp) {
      if (tmpOtherCard.value == card.value) {
        print("Du hast die Karte " + card.name + " auf der Hand.\n")
        print("Moechtest du sie dazu legen? (j/n)\n")
        line = scanner.nextLine()
        line match {
          case "ja" | "j" => {
            cardOnField += playerInGame(0).cardOnHand(playerInGame(0).cardOnHand.indexOf(card))
            playerInGame(0).cardOnHand.remove(playerInGame(0).cardOnHand.indexOf(card))
          }
          case "nein" | "n" =>
          //canBeat = false

          case _ =>
        }
      }
    }
  }

  def printCardOnField(): Unit = {
    print("\nAuf dem Spielefeld liegen nun folgende Karten:\n")
    for (card <- cardOnField) {
      print(card.name + " | ")
    }
    print("\n")
  }

  def printBeatenCards(): Unit = {
    print("\nAuf dem Spielefeld liegen nun folgende Karten:\n")
    for (card <- beatenCard) {
      print(card.name + " | ")
    }
  }

  def changeActualPlayer(actPlayer: Player): Unit = {
    if (actPlayer == playerInGame(0)) {
      actualPlayer = playerInGame(1)
    } else {
      actualPlayer = playerInGame(0)
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
    val player = r.nextInt(playerInGame.length)
    playerInGame(player)
  }

  def pullCard(): Unit = {
    actualPlayer.cardOnHand ++= cardOnField
    actualPlayer.cardOnHand ++= beatenCard
    cardOnField.clear()
    beatenCard.clear()
    print(actualPlayer.name + " muss alle Karten aufnehmen!\n")
  }

  // Karten des menschlichen Spielers ausgeben
  def printPlayerCards(): Unit = {
    print("Du hast folgende Karten auf der Hand:\n")
    for (card <- playerInGame(0).cardOnHand) {
      print(card.name + "\n")
    }
    print("Der Gegner hat folgende Karten auf der Hand:\n")
    for (card <- playerInGame(1).cardOnHand) {
      print(card.name + "\n")
    }
  }

  def chooseCardOnHand(): Unit = {
    print("Welche Karte moechtest du legen?\n")
    for (i <- 0 to actualPlayer.cardOnHand.length - 1) {
      print(i + 1 + " = " + actualPlayer.cardOnHand(i).name + " | ")
    }
    print("\n")
    line = scanner.nextLine()
    while (line.toInt <= 0 && line.toInt > playerInGame(0).cardOnHand.length + 1) {
      print("Du hast diese Karte nicht auf der Hand!\nProbiere es noch einmal..")
      line = scanner.nextLine()
    }
    cardOnField += actualPlayer.cardOnHand(line.toInt - 1)
    actualPlayer.cardOnHand.remove(line.toInt - 1)
  }

  def toJson: JsValue = ???

  // Karten an alle Spieler ausgeben (vor jeder Runde)
  def dealOut(): Unit = {
    for (i <- playerInGame) {
      while (i.cardOnHand.length < 6 && !deck.isEmpty()) {
        i.cardOnHand.append(deck.dealOut())
        cardsLeft -= 1
      }
      if (deck.isEmpty()) {
        print("Das Deck ist leer!\n")
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

  // Computergegner soll je nach schwierigkeitsgrad angreifen
  def cpuAttacks(): Unit = {
    var canLay = 0
    tmpCard.clear()
    tmpCard ++= cardOnField
    tmpCard ++= beatenCard
    if (tmpCard.isEmpty) {
      var lowestCard: Card = Card("Joker", allCards, "j")
      for (card <- playerInGame(1).cardOnHand) {
        if (card.value < lowestCard.value) {
          lowestCard = card
        }
      }
      cardOnField.append(lowestCard)
      playerInGame(1).cardOnHand.remove(playerInGame(1).cardOnHand.indexOf(lowestCard))
    }
    val r = scala.util.Random
    val attackChance = r.nextInt(3) + 1
    val tmpField = tmpCard
    val tmpHand = playerInGame(1).cardOnHand
    difficulty match {
      case 2 => {
        breakable {
          for (cardFromField <- tmpCard) {
            canLay = 0
            for (i <- tmpHand) {
              if (attackChance <= 2 && i.value == cardFromField.value) {
                cardOnField.append(i)
                playerInGame(1).cardOnHand.remove(playerInGame(1).cardOnHand.indexOf(i))
                canLay += 1
              }
            }
            if (canLay == 0) {
              break()
            }
          }
        }
      }
      case 3
      => for (cardFromField <- tmpCard) {
        for (i <- tmpHand) {
          if (i.value == cardFromField.value) {
            cardOnField.append(i)
            playerInGame(1).cardOnHand.remove(playerInGame(1).cardOnHand.indexOf(i))
          }
        }
      }
    }
  }

  // Kann die Karte geschlagen werden?
  def canBeatCard(cardFromField: Card, cardFromHand: Card): Boolean = {
    val tmpField = cardFromField
    val tmpHand = cardFromHand
    if ((tmpHand.value > tmpField.value &&
      tmpHand.symbol == tmpField.symbol) ||
      (!isTrump(tmpField) && isTrump(tmpHand)) ||
      (isTrump(tmpHand) && isTrump(tmpField) &&
        tmpHand.value > tmpField.value)) {
      true
    } else {
      false
    }
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