package de.htwg.se.durak.aview.tui

import de.htwg.se.durak.controller._
import de.htwg.se.durak.model._
import de.htwg.se.durak.util.Observable
import de.htwg.se.durak.util.Observer

import com.typesafe.scalalogging.{LazyLogging, LazyLogging}

import swing._

class Tui(c: Controller) extends Reactor {

  var condition = "amountPlayer"

  listenTo(c)

  reactions += {
    case e: Start => {
      initialize()
      printGameStart
      condition = "startGame"
    }

    case e: GameNew => {
      printGameStart
      condition = "startGame"
    }
    case e: AmountPlayer => {
      print("Wie viele Spieler spielen mit? (min 2, max 4)\n")
      condition = "amountPlayer"
    }
    case e: Difficulty => {
      print("Wie schwer soll das Spiel werden?\n")
      print("1 = leicht | 2 = mittel | 3 = schwer\n")
      this.difficulty = c.difficulty
      condition = "difficulty"
    }
    case e: AttackPlayer => {
      print("Du bist an der Reihe!\n")
      print("Mit welcher Karte moechtest du angreifen?\n")
    }
    case e: PushCard => {

    }
    case e: BeatCard => {

    }
    case e: PutCard => {

    }
    case e: GameLost => {
      lost
    }
    case e: GameWon => {
      won
    }
    case e: saveGame => {
      print("Moechtest du das Spiel wirklich speichern? (ja|nein)\n")
      condition = "saveGame"
    }
    case e: loadGame => {
      print("Moechtest du das Spiel wirklich laden? (ja|nein)\n")
      condition = "loadGame"
    }
    case e: mainMenu => {

    }
  }

  var difficulty = 1
  var continue = true

  def interpret(input: String) = {

    input match {
      case "quit" => continue = false
      case "init" => c.gameReset()
      case "difficulty" => setDifficulty(input.toInt)
      case "amountPlayer" => setAmountPlayer("4")
      case "attack" => attackPlayer(input, c.actualPlayer)
      case "chooseCard" => {

      }
      case "push" => {
        print("Du willst also schieben du Schlawiener!")
        state = "push"
      }
      case "beat" => {
        print("Du willst also schalgen du Schlawiener!")
        state = "beat"
      }
      case "load" =>
      case "save" =>
      case _ => {
        state match {
          case "beatOrPush" => {

          }
        }
      }
    }
    continue
  }

  def beatOrPush(): Unit = {
    print("Moechtest du schlagen oder schieben?\n")
    print("(beat | push)\n")

  }

  def chooseCardOnField(): Unit = {
    print("Welche Karte moechtest du schlagen?\n")
    for (i <- 0 to c.cardOnField.length - 1) {
      print(i + 1 + " = " + c.cardOnField(i).name + " | ")
    }
  }

  def chooseCardOnHand(): Unit = {
    print("Welche Karte moechtest du auswaehlen?\n")
    for (i <- 0 to c.playerInGame(0).cardOnHand.length - 1) {
      print(i + 1 + " = " + c.playerInGame(0).cardOnHand(i).name + " | ")
    }
  }

  def beatCard(attackCard: Int, beatCard: Int): Unit = {
    c.beatCard(attackCard, beatCard)
  }

  def attackPlayer(input: String, player: PlayerInterface): Unit = {

  }

  def setDifficulty(dif: Int): Unit = {
    dif match {
      case 1 => {
        c.setDifficulty(1)
        print("Das Spiel wurde auf leicht gestellt!")
      }
      case 2 => {
        c.setDifficulty(2)
        print("Das Spiel wurde auf mittel gestellt!")
      }
      case 3 => {
        c.setDifficulty(3)
        print("Das Spiel wurde auf schwer gestellt!")
      }
      case _ => {
        print("Diesen Schwierigkeitsgrad gibt es nicht.\n")
      }
    }
  }

  def won: Unit = {
    deafTo(c)
    print("Du hast gewonnen!\n")
  }

  def lost: Unit = {
    deafTo(c)
    print("Du bist ein Durak!\n")
  }

  def setAmountPlayer(amountOfPlayer: String): Unit = {
    amountOfPlayer match {
      case "0" => {
        print("Ein Spiel ohne Spieler ist kein Spiel...\n")
      }
      case "1" => {
        print("Du kannst nicht gegen dich selbst spielen!\n")
      }
      case "2" | "3" | "4" => {
        print("Es spielen " + amountOfPlayer + " mit!\n")
        c.initialize(amountOfPlayer.toInt)
      }
      case _ => {
        print("Diese Eingabe ist ungueltig!\n")
      }
    }
  }

  // Kann eine Aktion durchgefuehrt werden?
  def cantDoAnything: Boolean = {
    for (cardFromHand <- c.actualPlayer.cardOnHand) {
      if (!(c.canPushCard(c.cardOnField, cardFromHand))) false
      for (cardFromField <- c.cardOnField) {
        if (!(c.canBeatCard(cardFromField, cardFromHand))) false
      }
    }
    true
  }

  def pullCard: Unit = {
    c.actualPlayer.cardOnHand ++= c.beatenCard
    c.cardOnField.clear()
    print("Karten werden aufgenommen!\n")
  }

  def initialize(): Unit = {
    print("Das Spiel beginnt nun.\n")
  }

  def gameState: Unit = {
    print("Du hast " + c.actualPlayer.cardOnHand.size + " Karten auf der Hand\n")
    for (card <- c.actualPlayer.cardOnHand) {
      print(card.name + " ")
    }

    print("\n\nDeine Gegner haben folgende Karten:\n\n")
    for (player <- 1 to c.playerInGame.length + 1) {
      print(c.playerInGame(player) + "\n")
      for (card <- c.playerInGame(player).cardOnHand) {
        print(" ? ")
      }
    }

  }

  def saveGame: Unit = {

  }

  def loadGame: Unit = {

  }

  def exit: Unit = {
    print("Das Spiel wird nun beendet!\n")
    exit
  }

  def printGameStart: Unit = {
    print(c.actualPlayer + " ist an der Reihe.\n")
    print("Welche Karte moechtest du legen? (1 = 1. Karte, 2 = 2. Karte etc.)\n")
    for (i <- c.actualPlayer.cardOnHand) {
      print(i.name + " \n")
    }
  }
}