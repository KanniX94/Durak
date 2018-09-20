package de.htwg.se.durak.aview.tui

import de.htwg.se.durak.controller._
import de.htwg.se.durak.model._
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
    case e: GameStart => {
      GameState
      condition = "GameStart"
    }
    case e: GameNew => {
      printGameStart
      condition = "GameStart"
    }
    case e: AmountPlayer => {
      println("Wie viele Spieler spielen mit? (min 2, max 4")
      condition = "AmountPlayer"
    }
    case e: Difficulty => {
      println("Wie schwer soll das Spiel werden?")
      println("1 = leicht | 2 = mittel | 3 = schwer")
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
      case "difficulty" => setDifficulty(difficulty)
      case _ => {
      }
    }
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
        println("Diesen Schwierigkeitsgrad gibt es nicht.")
      }
    }
  }

  def won = {
    deafTo(c)
    println("Du hast gewonnen!")
  }

  def lost = {
    deafTo(c)
    println("Du bist ein Durak!")
  }

  def AmountPlayer(amountOfPlayer: String): Unit = {
    amountOfPlayer match {
      case "0" => {
        println("Ein Spiel ohne Spieler ist kein Spiel...")
      }
      case "1" => {
        println("Du kannst nicht gegen dich selbst spielen!")
      }
      case "2" | "3" | "4" => {
        println("Es spielen " + amountOfPlayer + " mit!")
        c.initialize(amountOfPlayer.toInt)
      }
      case _ => {
        println("Diese Eingabe ist ungueltig!")
      }
    }
  }

  def initialize(): Unit = {
    println("Das Spiel beginnt nun.\n")
    c.dealOut()
    c.printPlayerState()
  }

  def printGameStart: Unit = {
    println(c.actualPlayer + " ist an der Reihe.")
    println("Welche Karte moechtest du legen? (1 = 1. Karte, 2 = 2. Karte etc.)")
    for (i <- c.actualPlayer.cardOnHand) {
      println(i + " ")
    }
  }
}