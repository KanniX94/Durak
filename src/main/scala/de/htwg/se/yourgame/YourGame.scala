package de.htwg.se.yourgame

import de.htwg.se.yourgame.model.Player

object YourGame {
  def main(args: Array[String]): Unit = {
    val student = Player("Marcel Kanne")
    println("Hello, " + student.name)
  }
}
