package de.htwg.se.durak

import de.htwg.se.durak.aview.tui.Tui
import de.htwg.se.durak.controller.Controller
import de.htwg.se.durak.model.exactImpl.{Card, Deck, Player}

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object durakGame {
  def main(args: Array[String]): Unit = {
    val c = new Controller()
    val tui = new Tui(c)

    while (true) {
      val input = StdIn.readLine()
      tui.interpret(input)
    }
  }
}
