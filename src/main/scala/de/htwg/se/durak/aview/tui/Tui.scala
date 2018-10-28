package de.htwg.se.durak.aview

import java.awt.event.{KeyEvent, KeyListener}

import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.durak.controller.controllerComponent.{ControllerInterface, GameStatus}
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.util.Utils

class Tui(c: ControllerInterface) extends LazyLogging {
  welcomePlayer()

  def welcomePlayer(): Unit = {
    print("Willkommen zu Durak!\n")
    print("Du kannst folgende Aktionen durchfuehren:\n")
    print("start = Starte ein neues Spiel\n")
    print("save = Speichere deinen Spielstand\n")
    print("load = Lade einen Spielstand\n")
    print("exit = Beende das Spiel\n")
  }

  def keyTyped(e: KeyEvent): Unit = {
    //c.doAction(field, Utils.processKey(e.getExtendedKeyCode, e.getKeyChar))
  }

  var continue = true

  def interpret(input: String): Boolean = {

    input match {
      case "start" =>
        print("Das Spiel beginnt nun...\n")
        print("(ง ͠° ͟ل͜ ͡°)ง\n\n")
        c.initialize()
        welcomePlayer()
      case "exit" => continue = false
      case "save" => c.saveGame()
      case "load" =>
      case _ =>
    }
    continue
  }

  def initialize(): Unit = {
    print("Das Spiel beginnt nun.\n")
  }
}