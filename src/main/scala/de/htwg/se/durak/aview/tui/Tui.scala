package de.htwg.se.durak.aview.tui

import de.htwg.se.durak.controller._
import de.htwg.se.durak.model._

import scala.io.StdIn
import swing._

class Tui(c: Controller) extends Reactor {

  var condition = "GameStart"

  listenTo(c)

  reactions += {
    case e: Start => {
      initialize()
      printGameStart
      condition = "GameStart"
    }
    case e: StartRound => {
      gameState
      printGameStart
      condition = "GameStart"
    }
    case e: GameNew => {
      printGameStart
      condition = "GameStart"
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
  }

  var difficulty = 1

  def interpret(input: String): Unit = {
    condition match {
      case "startGame" =>
      case "difficulty" => setDifficulty(input.toInt)
      case "amountPlayer" => setAmountPlayer(input)
      case "attackPlayer" => attackPlayer(input, c.actualPlayer)
      case "chooseCard" => chooseCard()
      case "pushCard" =>
      case "beatCard" =>
      case _ => {
      }
    }
  }

  def chooseOrPush(): Unit = {
    print("Moechtest du schlagen oder schieben?\n")
    print("(1 = schlagen | 2 = schieben)\n")
    val tmp = StdIn.readLine()
  }

  def chooseCard(i: String): Unit = {
    print("Welche Karte moechtest du schlagen?\n")
    val card1 = StdIn.readLine()
    print("Mit welcher Karte moechtest du schlagen?\n")


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
    c.dealOut()
    c.printPlayerState()
  }

  def gameState: Unit = {
    print("Du hast " + c.actualPlayer.cardOnHand.size + " Karten auf der Hand\n")
    for (card <- c.actualPlayer.cardOnHand) {
      print(card.name + " ")
    }
    print("\n\nDeine Gegner haben folgende Karten:\n")
    for (player <- 1 to c.playerInGame.length + 1) {
      print(c.player.cardOnHand)
    }

  }

  def printGameStart: Unit = {
    print(c.actualPlayer + " ist an der Reihe.\n")
    print("Welche Karte moechtest du legen? (1 = 1. Karte, 2 = 2. Karte etc.)\n")
    for (i <- c.actualPlayer.cardOnHand) {
      print(i + " \n")
    }
  }
}