package de.htwg.se.durak

import de.htwg.se.durak.aview.tui.Tui
import de.htwg.se.durak.controller.Controller
import scala.io.StdIn._

object durakGame {
  val c = new Controller()
  val tui = new Tui(c)

  def main(args: Array[String]): Unit = {
    while (tui.interpret(readLine())) {}
  }
}
