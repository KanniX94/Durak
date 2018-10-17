package de.htwg.se.durak.aview

import java.awt.event.{KeyEvent, KeyListener}

import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.durak.controller.controllerComponent.{ControllerInterface, GameStatus}
import de.htwg.se.durak.model.FieldComponent.FieldInterface
import de.htwg.se.durak.util.Utils
//, field: FieldInterface
class Tui(c: ControllerInterface) extends LazyLogging {
  welcomePlayer()

  def welcomePlayer(): Unit = {
    print("Willkommen zu Durak!\n")
    print("Du kannst folgende Aktionen durchfuehren:\n")
    print("A = Kartenindex nach links\n")
    print("D = Kartenindex nach rechts\n")
    print("W = Schlagen\n")
    print("S = Schieben\n")
    print("P = Aufnehmen\n")
    print("N = Nichts unternehmen\n")
    print("X = Spiel beenden\n")
    print("Z = Spiel speichern\n")
    print("L = Spiel laden\n")
    print("U = Undo\n")
    print("R = Redo\n")
    print("Das Spiel beginnt nun...\n")
    print("(ง ͠° ͟ل͜ ͡°)ง\n\n")
  }

  def keyTyped(e: KeyEvent): Unit = {
    //c.doAction(field, Utils.processKey(e.getExtendedKeyCode, e.getKeyChar))
  }

  var continue = true

  def interpret(input: String): Boolean = {

    input match {
      case "start" => c.initialize()
      case "quit" => continue = false
      case _ => {

      }
    }
    continue
  }

  def initialize(): Unit = {
    print("Das Spiel beginnt nun.\n")
  }

  def saveGame(): Unit = {

  }

  def loadGame(): Unit = {

  }

  def exit(): Unit = {
    print("Das Spiel wird nun beendet!\n")
    sys.exit()
  }
}