package de.htwg.se.durak

import de.htwg.se.durak.model.Player

object durakGame {
  def main(args: Array[String]): Unit = {
    val student = Player("Marcel Kanne")
    println("Hello, " + student.name)
  }
}
