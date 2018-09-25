package de.htwg.se.durak.aview.tui

import de.htwg.se.durak.controller._
import de.htwg.se.durak.model._

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

    }
    case e: loadGame => {

    }
  }


  var difficulty = 1

  def interpret(input: String): Unit = {
    condition match {
      case "startGame" => initialize()
      case "difficulty" => setDifficulty(input.toInt)
      case "amountPlayer" => setAmountPlayer(input)
      case "attackPlayer" => attackPlayer(input, c.actualPlayer)
      case "chooseCard" =>
      case "pushCard" =>
      case "beatCard" =>
      case _ => {
      }
    }
  }

  def chooseOrPush(): Unit = {
    print("Moechtest du schlagen oder schieben?\n")
    print("(1 = schlagen | 2 = schieben)\n")

  }

  def chooseCard(): Unit = {
    print("Welche Karte moechtest du schlagen?\n")
    for (i <- 0 to c.cardOnField.length - 1) {
      print(i + 1 + " = " + c.cardOnField(i) + " | ")
    }
    print("Mit welcher Karte moechtest du schlagen?\n")
    for (i <- 0 to c.playerInGame(0).cardOnHand.length - 1) {
      print(i + 1 + " = " + c.playerInGame(0).cardOnHand(i) + " | ")
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